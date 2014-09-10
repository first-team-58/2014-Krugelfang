/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Team58
 */
public class autonomous {

    public static Timer autoTime = new Timer();
    public static int select = 1;

    public static void Reinit() {
        autoTime.stop();
        autoTime.reset();
        autoTime.start();
    }

    public static void doAuto() {
        int selected = ((Integer)Team582013.autoChooser.getSelected()).intValue();
        switch (selected){
            case 1:
                doNothing();
                System.out.println("Nothing");
                break;
            case 2 :
                leftSideAngle();
                System.out.println("Left Side");
                break;
                
            case 3: 
                middleAngle();
                System.out.print("Middle Side");
                break;
                
            case 4:
            rightSideAngle();
            System.out.println("Ride Side Angle");
            break;
        }
        
        
        //rightSide();
    }

    public static void rightSide() {

        double time = autoTime.get();
        int subtime = (int) ((time - (int) time) * 10);
        Shooter.controlWheel(true);
        boolean targetd = CalculateShot.processData(true, false);
        if (targetd && time > 3.5) {
            Shooter.fire(subtime * 1.5 < 5);
           // Drive.doManualDrive(0, 0, 0);

        }
        //autoTime.stop();
    }
    static boolean targeted = false;
    //Right Side Of The Pyramid

    public static void leftSide() {

        System.out.println("RightSide");
        double time = autoTime.get();
        int subtime = (int) ((time - (int) time) * 10);
        Shooter.controlWheel(true);
        boolean targetd = true;
        if (time >= 0.0 && time < 1.2) {
           // Drive.doManualDrive(-80, .7, 0);
        } else if (time > .8) {
            if (!targeted) {
                targetd = CalculateShot.processData(subtime % 2 == 0, true);
            }
            if (targetd && time > 6) {
                targeted = true;
                Shooter.fire(subtime * 1.5 < 5);
           //     Drive.doManualDrive(0, 0, 0);
            }
        }
        //autoTime.stop();
    }
    public static void leftSideAngle() {
            double time = autoTime.get();
     int subtime = (int) ((time - (int) time) * 10);

        if (time < 1.5) {
         //   Drive.doManualDrive(180, .7, 0);
        }
        else if (time > 1.5 && time <2.2) {
       // Drive.doManualDrive(0, 0, .7);
        }
        else {
           // Drive.doManualDrive(0, 0, 0);
        }
    }
    
     //Right Autonomous with Angle
    public static void rightSideAngle() {
        double time = autoTime.get();
     int subtime = (int) ((time - (int) time) * 10);

        if (time < 4) {
            Shooter.setAngle(3.17);  //295
        }
        else if (time > 4 && time <11) {
            Shooter.controlWheel(true);
            Shooter.adjustAngle(0);
            if (time > 6) {
             Shooter.fire(subtime * 1.5 < 5);
            }
        }
        else if (time >= 11 && time <11.2) {
        Drive.doManualDrive( .8, 0);
    }
        else if (time >= 11.2 && time < 13.2) {
            Drive.doManualDrive( 1, 0);
        }
        else if (time >= 13.2 && time < 14) {
        Drive.doManualDrive( 0, .7);
    }
        else {
            Drive.doManualDrive( 0, 0);
        }
        
    }
    
     //Middle Autonomous with Angle
    public static void middleAngle() {
        double time = autoTime.get();
     int subtime = (int) ((time - (int) time) * 10);
     if(time < .7) {
           Drive.doManualDrive( .5, 0); 
             }
     else if (time > .7 && time < 4.7) {
            Shooter.setAngle(3.25);
            Drive.doManualDrive( 0, 0);
        }
        else if (time > 4.7) {
         //   Drive.doManualDrive(0, 0, 0);
            Shooter.controlWheel(true);
            Shooter.adjustAngle(0);
            if (time > 6.7) {
             Drive.doManualDrive(0, 0);
             Shooter.fire(subtime * 1.5 < 5);
            }
        }
        
    }

    public static void doNothing() {
        System.out.println("Doing Nothing");
    }
}
