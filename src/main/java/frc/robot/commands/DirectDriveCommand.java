package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.DrivingSubsystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DirectDriveCommand extends CommandBase {
	
	private final DrivingSubsystem drivingSubsystem;
	public DirectDriveCommand(DrivingSubsystem subsystem) {
	  // Use addRequirements() here to declare subsystem dependencies.
	  drivingSubsystem = subsystem;
	  addRequirements(drivingSubsystem);
	}
  

	public void initialize() {
		// Make sure the controller is initialized before we try to use it.
		if (Robot.oi.controller1 == null)
			throw new NullPointerException("Controller was null.");

	}

	public void execute() {
		Robot.drivingSubsystem.drive(Robot.oi.controller1.getDrive(), Robot.oi.controller1.getTurn()); // Use input directly from the controller
	}

	public boolean isFinished() {
		return false; // We don't want the command to stop, we want it to be interrupted.
	}

	protected void end() {
		Robot.drivingSubsystem.stop();
	}

}