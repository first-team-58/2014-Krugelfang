// @author Team58
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.DigitalOutput;


public class Drive {
    
    private static Jaguar m_LMotor = new Jaguar(1), 
                          m_RMotor= new Jaguar(2);
    
    private static DigitalOutput m_LBreak = new DigitalOutput(1),
                                 m_RBreak = new DigitalOutput(2);
    
    private static RobotDrive m_robotDrive = new RobotDrive(m_LMotor, m_RMotor );
    
    public static void Init(){}
    public static void DoDrive(){
        DoManualDrive(Controls.controller.getDirectionDegrees(), Controls.controller.getMagnitude() );
    }
    
    public static void DoManualDrive(double direction, double magnitude ){
        m_robotDrive.arcadeDrive(magnitude, direction, true);
    }
}
