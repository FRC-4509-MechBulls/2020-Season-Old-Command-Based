package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DirectDriveCommand;
import frc.robot.commands.WheelArmCommand;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

// Controls the drive motors
public class ArmSubsystem extends Subsystem {
    public static double baseGrabberSpeed = 0.95;

    @Override
	public void initDefaultCommand() {
		this.setDefaultCommand(new WheelArmCommand());
    }
    public void set(double speed) {
		if(Math.abs(speed) > 1)
			speed = Math.abs(speed) / speed;
		RobotMap.grabberTalon.set(speed * baseGrabberSpeed);
	}
	public void stop() {
		RobotMap.grabberTalon.set(0);
	}

}
	