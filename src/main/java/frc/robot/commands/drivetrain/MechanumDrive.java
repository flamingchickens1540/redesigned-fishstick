package frc.robot.commands.drivetrain;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.utils.MathUtils;
import org.opencv.core.Mat;

public class MechanumDrive extends CommandBase {
    private final Drivetrain drivetrain;
    private final CommandXboxController driver;

    public MechanumDrive(Drivetrain drivetrain, CommandXboxController driver) {
        this.drivetrain = drivetrain;
        this.driver = driver;
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        double x = -MathUtils.deadzone(driver.getLeftX() * 0.5, 0.1);
        double y = MathUtils.deadzone(driver.getLeftY() * 0.5, 0.1);
        double magnitude = Math.hypot(x, y);
        Rotation2d driveHeading = new Rotation2d(x, y);
        x = driveHeading.rotateBy(drivetrain.getHeading()).getCos() * magnitude;
        y = driveHeading.rotateBy(drivetrain.getHeading()).getSin() * magnitude;
        double z = -MathUtils.deadzone(driver.getRightX(), 0.1);

        double lF = y + z + x;
        double lB = y + z - x;
        double rF = y - z - x;
        double rB = y - z + x;

        double maxVal = Math.abs(MathUtils.max(lF, rF, lB, rB));
        if(maxVal > 1){
            lF /= maxVal;
            rF /= maxVal;
            lB /= maxVal;
            rB /= maxVal;
        }

       drivetrain.setAllPercent(lF, lB, rF, rB);

    }
}
