/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Robotics
 */
public class Autonomous {

    //drives robot into scoring range
    public static Timer totalTime = new Timer();
    public static boolean hotGoal = true;
    public static double delay = 0;

    public static void startTime() {
        totalTime.stop();
        totalTime.reset();
        totalTime.start();
    }

    public static void driveToShoot() {
        double elapTime = totalTime.get();
        double distancewall = Driverstation.getDistance();
        System.out.println("Distance" + distancewall);
        
        
         if(elapTime < .1) {
                 hotGoal = Driverstation.hotGoal();
                  if (hotGoal = false) {
                delay = 5;
            }
            }
         
        if (elapTime< (3.1+delay)) {
          
           
       
        if (distancewall > 160) {
            Motors.DriveBase.mecanumDrive_Polar(.8, 0, 0);
        } else {
            Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);
        }
        }
        else if (elapTime > (3.1 + delay) && elapTime < (3.6+delay)) {
            Motors.MakePaddleArmGo(1);
        }
        else if (elapTime > (3.6+delay) && elapTime < (3.7+ delay) )
            Motors.MakePaddleArmGo(0);
        else if (elapTime > (3.7 + delay) && elapTime < (7 + delay)) {
            Motors.SetBrakes(false);
        }
        else {
            Motors.MakePaddleArmGo(0);
            //Motors.SetBrakes(false);
        }
        
        

    }
}
