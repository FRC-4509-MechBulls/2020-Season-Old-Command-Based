package frc.robot;

import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.commands.DirectDriveCommand;
import frc.robot.controls.ControllerBase;
import frc.robot.controls.XboxController1;
import frc.robot.controls.XboxController2;

public class OI {
	public static final int XBOX_CONTROLLER_1_PORT = 0;
    public static final int XBOX_CONTROLLER_2_PORT = 1;
    public XboxController1 controller1;
    public XboxController2 controller2;
    DriveTrigger driveTrigger;

    public OI(){
        this.controller1 = new XboxController1(OI.XBOX_CONTROLLER_1_PORT);
        this.controller2 = new XboxController2(OI.XBOX_CONTROLLER_2_PORT);
    }
    public void setTriggers(){
        this.driveTrigger.whileActive(new DirectDriveCommand());


    }

	class DriveTrigger extends Trigger {
		public boolean get() { return (Robot.oi.controller1.getDrive() != 0 || Robot.oi.controller1.getTurn() != 0); }
    }
    

}