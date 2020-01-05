package frc.robot.controls;
import edu.wpi.first.wpilibj.GenericHID;

public class XboxController2{
    edu.wpi.first.wpilibj.XboxController controller2;
    public XboxController2(int port){
        this.controller2 = new edu.wpi.first.wpilibj.XboxController(port);
    }
    
    public double getArm(){
        double n = this.controller2.getY(GenericHID.Hand.kRight);
		return Math.abs(n) < 0.1 ? 0 : n;
    }
    public boolean setEncoder(){
        return this.controller2.getYButton();
    }
}