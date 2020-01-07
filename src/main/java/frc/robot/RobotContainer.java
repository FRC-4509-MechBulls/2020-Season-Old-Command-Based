package frc.robot;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DirectDriveCommand;
import frc.robot.commands.SetEncoderCommand;
import frc.robot.commands.WheelArmCommand;
import frc.robot.controls.XboxController1;
import frc.robot.controls.XboxController2;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DrivingSubsystem;

public class RobotContainer {

    public XboxController1 controller1;
    public XboxController2 controller2;
    

    DriveTrigger driveTrigger;
    GrabberTrigger grabberTrigger;
    EncoderTrigger encoderTrigger;

    DrivingSubsystem drivingSubsystem;
    ArmSubsystem armSubsystem;

    public RobotContainer(){
        this.controller1 = new XboxController1(Constants.XBOX_CONTROLLER_1_PORT);
        this.controller2 = new XboxController2(Constants.XBOX_CONTROLLER_2_PORT);
        configureButtonBindings();

    }
    public void setTriggers(){
        driveTrigger.whileActive(new DirectDriveCommand());
        grabberTrigger.whileActive(new WheelArmCommand());
        encoderTrigger.whileActive(new SetEncoderCommand());
    
    }

	class DriveTrigger extends Trigger {
		public boolean get() { return (Robot.oi.controller1.getDrive() != 0 || Robot.oi.controller1.getTurn() != 0); }
    }
    class GrabberTrigger extends Trigger{
        public boolean get(){ return (Robot.oi.controller2.getArm() != 0);}
    }
    class EncoderTrigger extends Trigger{
        public boolean get(){return Robot.oi.controller2.setEncoder();}
    }
    
    private void configureButtonBindings() {
    }
  
  
    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
//     public Command getAutonomousCommand() {
//       // An ExampleCommand will run in autonomous
//      // return m_autoCommand;
//     }
 }