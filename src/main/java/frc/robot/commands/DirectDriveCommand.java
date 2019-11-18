package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DirectDriveCommand extends Command {
	
	public DirectDriveCommand() {
		requires(Robot.drivingSubsystem); // Tells the Scheduler that this command will need the DrivingSubsystem.
	}

	protected void initialize() {
		// Make sure the controller is initialized before we try to use it.
		if(Robot.oi.controller1 == null) throw new NullPointerException("Controller was null.");

	}

	public void execute() {
		Robot.drivingSubsystem.drive(Robot.oi.controller1.getDrive(), Robot.oi.controller1.getTurn()); // Use input directly from the controller
	}

	protected boolean isFinished() {
		return false; // We don't want the command to stop, we want it to be interrupted.
	}

	protected void end() {
		Robot.drivingSubsystem.stop();
	}

}