/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static final int LEFT_FRONT_DRIVE_TALON_PORT  = 6;
	public static final int LEFT_BACK_DRIVE_TALON_PORT   = 7;
	public static final int RIGHT_FRONT_DRIVE_TALON_PORT = 1;
	public static final int RIGHT_BACK_DRIVE_TALON_PORT  = 3;


	public static WPI_TalonSRX leftFrontDriveTalon;
	public static WPI_TalonSRX leftBackDriveTalon;
	public static WPI_TalonSRX rightFrontDriveTalon;
	public static WPI_TalonSRX rightBackDriveTalon;
	public static DifferentialDrive drive;

	
	// Initialize anything related to driving (motor controllers, encoders, etc.)
	public static void initDrive() {
		RobotMap.leftFrontDriveTalon  = new WPI_TalonSRX(RobotMap.LEFT_FRONT_DRIVE_TALON_PORT);
		RobotMap.leftBackDriveTalon   = new WPI_TalonSRX(RobotMap.LEFT_BACK_DRIVE_TALON_PORT);
		RobotMap.rightFrontDriveTalon = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_DRIVE_TALON_PORT);
		RobotMap.rightBackDriveTalon  = new WPI_TalonSRX(RobotMap.RIGHT_BACK_DRIVE_TALON_PORT);
		
		RobotMap.leftBackDriveTalon.follow(RobotMap.leftFrontDriveTalon);
		RobotMap.rightBackDriveTalon.follow(RobotMap.rightFrontDriveTalon);
		
		RobotMap.drive = new DifferentialDrive(RobotMap.leftFrontDriveTalon, RobotMap.rightFrontDriveTalon);
		
		// RobotMap.drive.setDeadband(0);
	}
	


}
