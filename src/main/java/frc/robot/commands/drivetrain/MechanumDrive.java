package frc.robot.commands.drivetrain;

import edu.wpi.first.math.MathUtil;
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
        double x = MathUtils.deadzone(driver.getLeftX() * 0.5, 0.1);
        double y = MathUtils.deadzone(driver.getLeftY() * 0.5, 0.1);

        double theta = Math.atan2(y, x);
        double radius = Math.hypot(x, y);

        double sin = Math.sin(theta - Math.PI/4);
        double cos = Math.cos(theta - Math.PI/4);
        double max = Math.max(sin, cos);

        double rightX = MathUtils.deadzone(driver.getRightX(), 0.1);

        double lF = radius * cos/max + rightX;
        double rF = radius * sin/max - rightX;
        double lB = radius * sin/max + rightX;
        double rB = radius * cos/max - rightX;

        double maxVal = Math.abs(MathUtils.max(lF, rF, lB, rB));
        if(maxVal > 1){
            lF/= maxVal;
            rF/= maxVal;
            lB/= maxVal;
            rB/= maxVal;
        }

       drivetrain.setAllPercent(lF, lB, rF, rB);

    }
}
