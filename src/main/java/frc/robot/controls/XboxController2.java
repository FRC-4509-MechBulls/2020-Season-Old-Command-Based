package frc.robot.controls;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

public class XboxController2{
    XboxController controller2;
    public XboxController2(int port){
        controller2 = new XboxController(port);
    }
    
    public double getArm(){
        double n = controller2.getY(GenericHID.Hand.kRight);
		return Math.abs(n) < 0.1 ? 0 : n;
    }
    public boolean setEncoder(){
        return controller2.getYButton();
    }
}