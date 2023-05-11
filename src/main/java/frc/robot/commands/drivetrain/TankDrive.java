package frc.robot.commands.drivetrain;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.utils.MathUtils;

public class TankDrive extends CommandBase {
    private final Drivetrain drivetrain;
    private final CommandXboxController driver;

    public TankDrive(Drivetrain drivetrain, CommandXboxController driver){
        this.drivetrain = drivetrain;
        this.driver = driver;
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        drivetrain.setLRPercent(MathUtils.deadzone(driver.getLeftY()*0.5, 0.1),MathUtils.deadzone(driver.getRightY()*0.5, 0.1));
    }
}
