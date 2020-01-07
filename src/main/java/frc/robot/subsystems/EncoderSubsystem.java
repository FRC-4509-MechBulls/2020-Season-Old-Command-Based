/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.Constants;

/**
 * Add your docs here.
 */
public class EncoderSubsystem extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static DifferentialDrive drive;

 
  public static void initEncoder(){
    WPI_TalonSRX leftMotors = new WPI_TalonSRX(2);
    WPI_TalonSRX rightMotors = new WPI_TalonSRX(3);
    drive = new DifferentialDrive(leftMotors, rightMotors);
    Constants.leftEncoder.setDistancePerPulse(Constants.encoderDistance);
		Constants.rightEncoder.setDistancePerPulse(Constants.encoderDistance);
    Constants.rightEncoder.reset();
    Constants.leftEncoder.reset();
  }
  public void motorSet() {
    //double error = Constants.leftEncoder.getDistance() - Constants.rightEncoder.getDistance();

    // Drives forward continuously at half speed, using the encoders to stabilize the heading
   System.out.println(Constants.leftEncoder.getDistance());
   System.out.println(Constants.rightEncoder.getDistance());

    if(Constants.leftEncoder.get() < 256 && Constants.rightEncoder.get() < 256 ) {
       drive.tankDrive(0.25,0.25);
   } else {
       drive.tankDrive(0, 0);
   }
 
  }
  public void stop() {
   drive.tankDrive(0, 0);
  }
}
