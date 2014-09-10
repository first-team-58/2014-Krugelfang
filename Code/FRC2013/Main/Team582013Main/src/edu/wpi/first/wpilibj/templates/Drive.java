/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 *author Robotics
 */
public class Drive {
    //Initalize Jaguars
    private static Victor mLeftFront = new Victor(4),
        mRightFront = new Victor (2),
        mLeftBack = new Victor (3),
        mRightBack = new Victor (1);       


    private static RobotDrive m_RobotDrive = new RobotDrive(mLeftFront, mLeftBack, mRightFront, mRightBack);

    
    //Main Drive
        public static void doDrive() {
           boolean goSlow = Controls.driver.getRawButton(7) || Controls.driver.getRawButton(8);
           
           double direction = Controls.driver.getDirectionDegrees();
           double magnitude = Controls.driver.getMagnitude();
           double rotation = Controls.driver.getRawAxis(4);
           
           if (goSlow) {
               magnitude *= .5;
           }
           
           doManualDrive(direction, magnitude, rotation);
        }
        
        public static void doManualDrive(double direction, double magnitude, double rotation) {
            
            if (magnitude < .1) {
                magnitude = 0.;
            }
          
            magnitude *= .6;
            m_RobotDrive.mecanumDrive_Polar(magnitude, direction, rotation);
        }
        }