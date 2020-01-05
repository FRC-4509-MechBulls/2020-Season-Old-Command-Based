package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.Constants;
import frc.robot.commands.DirectDriveCommand;
import frc.robot.commands.WheelArmCommand;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

// Controls the drive motors
public class ArmSubsystem extends Subsystem {
    public static double baseGrabberSpeed = 0.95;
	public static WPI_TalonSRX grabberTalon;
    @Override
	public void initDefaultCommand() {
		this.setDefaultCommand(new WheelArmCommand());
    }
    public void set(double speed) {
		if(Math.abs(speed) > 1)
			speed = Math.abs(speed) / speed;
		grabberTalon.set(speed * baseGrabberSpeed);
	}

	public static void initArm(){
		grabberTalon = new WPI_TalonSRX(Constants.GRABBER_TALON_PORT);
	}

	public void stop() {
		grabberTalon.set(0);
	}

}
	