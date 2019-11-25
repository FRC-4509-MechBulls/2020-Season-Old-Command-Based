package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class WheelArmCommand extends Command {
	
	public WheelArmCommand() {
		requires(Robot.armSubsystem); // Tells the Scheduler that this command will need the DrivingSubsystem.
	}

	protected void initialize() {
		// Make sure the controller is initialized before we try to use it.
		

	}

	public void execute() {
        Robot.armSubsystem.set(Robot.oi.controller2.getArm());

	}

	protected boolean isFinished() {
		return false; // We don't want the command to stop, we want it to be interrupted.
	}

	protected void end() {
		Robot.armSubsystem.stop();
	}

}