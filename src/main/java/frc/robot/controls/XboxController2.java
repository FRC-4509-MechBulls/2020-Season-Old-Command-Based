package frc.robot.controls;
import edu.wpi.first.wpilibj.GenericHID;

public class XboxController2 implements ControllerBase{
    edu.wpi.first.wpilibj.XboxController controller2;
    public XboxController2(int port){
        this.controller2 = new edu.wpi.first.wpilibj.XboxController(port);
    }
    @Override
	public double getDrive() {
		return 0.0;
	}
	
	@Override
	public double getTurn() {
        return 0.0;
    }
    public double getArm(){
        double n = -1 * this.controller2.getY(GenericHID.Hand.kRight);
		return Math.abs(n) < 0.1 ? 0 : n;
    }
}