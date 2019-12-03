package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DirectDriveCommand;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

// Controls the drive motors
public class DrivingSubsystem extends Subsystem {
	

	public double baseDriveSpeed = 0.90;
	



	@Override
	public void initDefaultCommand() {
		this.setDefaultCommand(new DirectDriveCommand());
	}
	
	public void drive(double ySpeed, double rotation) {
		
		if(Math.abs(ySpeed) > 1)
			ySpeed = Math.abs(ySpeed) / ySpeed; // if the value given was too high, set it to the max
		ySpeed *= baseDriveSpeed; // scale down the speed
		
		
		if(Math.abs(rotation) > 1)
			rotation = Math.abs(rotation) / rotation; // if the value given was too high, set it to the max
		rotation *= baseDriveSpeed; // scale down the speed
		
		RobotMap.drive.arcadeDrive(ySpeed, rotation); // function provided by the drivetrain. controls y and turn speed at the same time.
	}
	

	// Directly set the speed of the talons to 0. If a command that sets the speed is still running, this won't stop it.
	public void stop() {
		RobotMap.leftFrontDriveTalon.set(0);
		RobotMap.rightFrontDriveTalon.set(0);
	}



	
}