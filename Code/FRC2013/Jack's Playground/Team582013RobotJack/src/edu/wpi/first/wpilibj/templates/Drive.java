// @author Team58
package edu.wpi.first.wpilibj.templates;
//import stuff
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Relay;

public class Drive {
    
    //name the jaguars
    private static Jaguar m_L1Motor = new Jaguar(1),
            m_R1Motor = new Jaguar(2),
            m_L2Motor = new Jaguar(3),
            m_R2Motor = new Jaguar(4);
    
    //name the breaks
//    private static DigitalOutput m_LFBreak = new DigitalOutput(1),
//            m_LRBreak = new DigitalOutput(2),
//            m_RFBreak = new DigitalOutput(3),
//            m_RRBreak = new DigitalOutput(4);
    
    //set motors for robotDrive
    private static RobotDrive m_robotDrive = new RobotDrive(m_L1Motor, m_L2Motor, m_R1Motor, m_R2Motor);

    public static void Init() {
    }

    public static void DoDrive() {

        boolean goSlow = Controls.getButton(5) || Controls.getButton(6);
        //grab and name the z-axis(triggers) on the controller
        //changed z axis to button
        boolean zAxis = Controls.controller.getRawButton(3);
        double zAxisValue = Controls.controller.getRawAxis(3);
        if (zAxisValue < -.5 || zAxisValue > .5) {
            zAxis = true;

        }
        
        //if we need the zAxis again
        if (zAxis) {
          
        } else {
           
        }
    }

//set 5WDdrive to tankdrive mode and add sensitivity
//    public static void Do5WDDrive(double moveValue, double rotateValue) {
//        if (moveValue < .1) {
//            moveValue = 0;
//        }
//        m_robotDrive.arcadeDrive(moveValue, rotateValue);
//    }

    public static void DoManualDrive(double moveValue, double rotateValue) {

        if (Controls.controller.getRawButton(1)) {
            moveValue = moveValue * .5;
        }

        if (moveValue < .1) {
            moveValue = 0.0;
        }

        m_robotDrive.arcadeDrive(moveValue, rotateValue);
    }
}
