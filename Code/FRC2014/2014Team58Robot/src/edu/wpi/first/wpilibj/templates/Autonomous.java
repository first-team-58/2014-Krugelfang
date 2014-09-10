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
        //double distancewall = Driverstation.getDistance();
        //System.out.println("Distance" + distancewall);
        
        
//         if(elapTime < .1) {
//                 hotGoal = Driverstation.hotGoal();
//                  if (hotGoal = false) {
//                delay = 5;
//            }
//            }
//         
      {
        if (elapTime < 1.6) {
        Motors.setBrakesDrive(false);
        Motors.DriveBase.mecanumDrive_Polar(.8, 0, 0);
        } else if (elapTime > 1.6 && elapTime < 1.7) {
            Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);
        }  else if (elapTime > 1.7 && elapTime < 2.2) {
            System.out.println("Shooting");
            Motors.MakePaddleArmGo(1);
             Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);
        }
        else if (elapTime > 2.2 && elapTime < 2.3)
            Motors.MakePaddleArmGo(0);
        else if (elapTime > 2.3 && elapTime < 5) {
            Motors.SetBrakes(false);
        }
        else {
            Motors.MakePaddleArmGo(0);
            //Motors.SetBrakes(false);
            Motors.setBrakesDrive(false);
            Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);

        }
        
        

    }
    }
}
