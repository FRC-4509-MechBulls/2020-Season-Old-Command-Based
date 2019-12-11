package frc.robot;

import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.commands.DirectDriveCommand;
import frc.robot.commands.WheelArmCommand;

import frc.robot.controls.XboxController1;
import frc.robot.controls.XboxController2;

public class OI {

    public XboxController1 controller1;
    public XboxController2 controller2;
    DriveTrigger driveTrigger;
    GrabberTrigger grabberTrigger;

    public OI(){
        this.controller1 = new XboxController1(RobotMap.XBOX_CONTROLLER_1_PORT);
        this.controller2 = new XboxController2(RobotMap.XBOX_CONTROLLER_2_PORT);
    }
    public void setTriggers(){
        this.driveTrigger.whileActive(new DirectDriveCommand());
        this.grabberTrigger.whileActive(new WheelArmCommand());

    }

	class DriveTrigger extends Trigger {
		public boolean get() { return (Robot.oi.controller1.getDrive() != 0 || Robot.oi.controller1.getTurn() != 0); }
    }
    class GrabberTrigger extends Trigger{
        public boolean get(){ return (Robot.oi.controller2.getArm() != 0);}
    }

}