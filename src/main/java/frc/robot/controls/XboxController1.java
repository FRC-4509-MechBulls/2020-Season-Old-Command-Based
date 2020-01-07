package frc.robot.controls;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

public class XboxController1{
    XboxController controller1;

    public XboxController1(int port){
        controller1 = new XboxController(port);
    }
	
	public double getDrive() {
		double n = controller1.getTriggerAxis(GenericHID.Hand.kRight) - controller1.getTriggerAxis(GenericHID.Hand.kLeft);
		return Math.abs(n) < 0.1 ? 0 : n;
	}
	

	public double getTurn() {
		double n = controller1.getX(GenericHID.Hand.kLeft);
		return Math.abs(n) < 0.1 ? 0 : n;
    }


		
	

}