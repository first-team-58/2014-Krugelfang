/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * author Robotics
 */
public class Drive {
    //Initalize Jaguars
    private static Relay trigger = new Relay(1);
    private static Victor mLeftFront = new Victor(4),
            mRightFront = new Victor(2),
            mLeftBack = new Victor(3),
            mRightBack = new Victor(1),
            deck = new Victor(8),
            shooter = new Victor(5);
    private static RobotDrive m_RobotDrive = new RobotDrive(mLeftFront, mLeftBack, mRightFront, mRightBack);

    //Main Drive
    public static void doDrive() {

        boolean goSlow = Controls.driver.getRawButton(5) || Controls.driver.getRawButton(6);
      //  double direction = Controls.driver.getDirectionDegrees();
        double rotation = Controls.driver.getRawAxis(4);
        double magnitude = Controls.driver.getMagnitude();
        double yDirection = Controls.driver.getY();
        magnitude=magnitude*yDirection;
        System.out.println("mag" + magnitude);

        if (goSlow) {
            magnitude *= .5;
        }

       
        if(Controls.driver.getRawButton(3)) {
            shooter.set(-1);
        }
        else {
            shooter.set(0);
        }
            if(Controls.driver.getRawButton(4)) {
                deck.set(.5);
            }
            else if(Controls.driver.getRawButton(1)) {
                deck.set(-.5);
            }
                else {
                deck.set(0);
            }
         if(Controls.driver.getRawButton(6)){
             trigger.set(Relay.Value.kOn);
         }  
         else{
             trigger.set(Relay.Value.kOff);
         }
            
//        }
//        if (Controls.driver.getRawButton(4)) {
//            deck.set(.7);
//        }
//        else if (Controls.driver.getRawButton(1)) {
//            deck.set(-.7);
//        }
//        else {
//            deck.set(0);
//        }
//        if(Controls.driver.getRawButton(6)) {
//            trigger.set(Relay.Value.kOn);
//        }
//        else {
//            trigger.set(Relay.Value.kOff);
//        }
         doManualDrive(magnitude, rotation);
    }
    

    public static void doManualDrive( double magnitude, double rotation) {

        if (magnitude < .1) {
            magnitude = 0.;
        }
       
        System.out.println(magnitude+" " + rotation);
        //System.out.println("d: "+direction+" m: "+magnitude+" r: "+ rotation);
        //magnitude *=   1; //Adjust for sensitivity do NOT CHANGE
        //m_RobotDrive.mecanumDrive_Polar(magnitude, direction, rotation);  //For mecanums
        m_RobotDrive.arcadeDrive(magnitude, rotation);
    }
}