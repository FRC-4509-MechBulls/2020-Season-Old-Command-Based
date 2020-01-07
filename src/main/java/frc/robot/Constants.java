/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The Constants is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class Constants {

	public static final int LEFT_FRONT_DRIVE_TALON_PORT  = 6;
	public static final int LEFT_BACK_DRIVE_TALON_PORT   = 7;
	public static final int RIGHT_FRONT_DRIVE_TALON_PORT = 1;
	public static final int RIGHT_BACK_DRIVE_TALON_PORT  = 3;
	public static final int GRABBER_TALON_PORT = 8;
	public static final int XBOX_CONTROLLER_1_PORT = 0;
    public static final int XBOX_CONTROLLER_2_PORT = 1;

	public static final Encoder leftEncoder = new Encoder(0,1);
	public static final Encoder rightEncoder = new Encoder(2,3);

	public static final WPI_TalonSRX elevatorTalon = new WPI_TalonSRX(2);
	public static final double encoderDiameter = 0.5; //in feet
	public static final double encoderDistance = encoderDiameter*3.14/1024;


	

	


}
