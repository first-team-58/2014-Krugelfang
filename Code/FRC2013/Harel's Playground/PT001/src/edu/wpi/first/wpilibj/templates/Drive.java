/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Jaguar;


/**
 *
 * @author student
 */
public class Drive {
    
    private static Jaguar m_LMotor = new Jaguar(1),
            m_RMotor = new Jaguar(2);
    
    private static RobotDrive m_robotDrive = new RobotDrive(m_LMotor, m_RMotor);

    

    
    
    public static void DoDrive(){
        
    double moveValue = Controls.driver.getRawAxis(1);
    double rotateValue = Controls.driver.getRawAxis(2);
        System.out.println("DRIVING!"+moveValue+"|"+rotateValue);
        m_robotDrive.arcadeDrive(moveValue, rotateValue);
        
    }
}

