package frc.robot.commands.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase{

    private final TalonSRX frontLeft = new TalonSRX(1);
    private final TalonSRX frontRight = new TalonSRX(4);
    private final TalonSRX backLeft = new TalonSRX(2);
    private final TalonSRX backRight = new TalonSRX(3);
    private final AHRS navx = new AHRS(SPI.Port.kMXP);
    private Rotation2d heading;

    public Drivetrain(){
        frontLeft.setNeutralMode(NeutralMode.Brake);
        backLeft.setNeutralMode(NeutralMode.Brake);
        frontRight.setNeutralMode(NeutralMode.Brake);
        backRight.setNeutralMode(NeutralMode.Brake);

        frontLeft.setInverted(true);
        backLeft.setInverted(true);
    }

    public Rotation2d getHeading() {
        return navx.getRotation2d();
    }

    public void resetHeading() {
        navx.reset();
    }

    public void setLRPercent(double leftPercent, double rightPercent){
        frontLeft.set(ControlMode.PercentOutput, leftPercent);
        backLeft.set(ControlMode.PercentOutput, leftPercent);
        frontRight.set(ControlMode.PercentOutput, rightPercent);
        backRight.set(ControlMode.PercentOutput, rightPercent);
    }

    public void setAllPercent(double frontLeftPercent, double backLeftPercent,  double frontRightPercent, double backRightPercent){
        frontLeft.set(ControlMode.PercentOutput, frontLeftPercent);
        backLeft.set(ControlMode.PercentOutput, backLeftPercent);
        frontRight.set(ControlMode.PercentOutput, frontRightPercent);
        backRight.set(ControlMode.PercentOutput, backRightPercent);
    }


    
}
