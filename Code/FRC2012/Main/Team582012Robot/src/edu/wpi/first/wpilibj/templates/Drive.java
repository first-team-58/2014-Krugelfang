// @author Team58
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Joystick;

public class Drive {
    private static Joystick m_controller = new Joystick(1);
    
    private static Jaguar m_LFMotor = new Jaguar(1), 
                          m_RFMotor= new Jaguar(2),
                          m_LRMotor = new Jaguar(3),
                          m_RRMotor = new Jaguar(4);
    
    private static DigitalOutput m_LFBreak = new DigitalOutput(1),
                                 m_LRBreak = new DigitalOutput(2),
                                 m_RFBreak = new DigitalOutput(3),
                                 m_RRBreak = new DigitalOutput(4);
    private static RobotDrive m_robotDrive = new RobotDrive(m_LFMotor, m_LRMotor, m_RFMotor, m_RRMotor);
    
    public static void DoDrive(){
        DoManualDrive(m_controller.getDirectionDegrees(), m_controller.getMagnitude(), m_controller.getTwist());
    }
    
    public static void DoManualDrive(double direction, double magnitude, double rotation){
        m_robotDrive.mecanumDrive_Polar(magnitude, direction, rotation);
    }
}
