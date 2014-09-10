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
public class Autonomous {
    
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
            Drive.doManualDrive(0, 0, 0);

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
            Drive.doManualDrive(-80, .7, 0);
        } else if (time > .8) {
            if (!targeted) {
                targetd = CalculateShot.processData(subtime % 2 == 0, true);
            }
            if (targetd && time > 6) {
                targeted = true;
                Shooter.fire(subtime * 1.5 < 5);
                Drive.doManualDrive(0, 0, 0);
            }
        }
        //autoTime.stop();
    }
    public static void leftSideAngle() {
            double time = autoTime.get();
     int subtime = (int) ((time - (int) time) * 10);

        if (time <= 1.1) {
            Drive.doManualDrive(-80, .7, 0);
            Shooter.setAngle(3.17);
        }
        else if (time > 1.1 && time <= 1.3) {
            Drive.doManualDrive(0, 0, 1);
        }
        else if (time > 1.3 && time < 5.3) {
            Drive.doManualDrive(0, 0, 0);
            Shooter.controlWheel(true);
            Shooter.adjustAngle(0);
            if (time > 7.3) {
             Shooter.fire(subtime * 1.5 < 5);
            }
        }
    }
    
     //Right Autonomous with Angle
    public static void rightSideAngle() {
        double time = autoTime.get();
     int subtime = (int) ((time - (int) time) * 10);

        if (time < 2.5) {
            Shooter.setAngle(3.23);  //295 //3.17  / THIS ANGLE
        }
        else if (time > 2.5 && time < 12) {
            Shooter.controlWheel(true);
            Shooter.adjustAngle(0);
            if (time > 4.5 && time <12) {
             Shooter.fire(subtime * 1.5 < 5);
            }
        }
        else if (time < 12 && time <12.1) {
       // Shooter.adjustAngle(0);    
        Shooter.controlWheel(false);
        Drive.doManualDrive(80, .7, 0);
    }
        else if (time >= 12.1 && time < 12.3) {
        Shooter.setAngle(2.99);    
        Drive.doManualDrive(180, .5, 0);
    }
           else if (time >= 12.3 && time < 13.1) {
        Shooter.setAngle(2.99);    
        Drive.doManualDrive(180, 1, 0);
    }
           else if (time >= 13.1 && time < 13.3) {
        Shooter.setAngle(2.99);    
        Drive.doManualDrive(180, .4, 0);
    }
             
           
        else if (time >= 13.3 && time < 13.8) {
            Drive.doManualDrive(0, 0, .7);
           Shooter.setAngle(2.99); 
        }
        else {
            Drive.doManualDrive(0,0,0);
            Shooter.adjustAngle(0);
        }
        
    }
    
     //Middle Autonomous with Angle
    public static void middleAngle() {
        double time = autoTime.get();
     int subtime = (int) ((time - (int) time) * 10);
     if(time < .7) {
           Drive.doManualDrive(0, .5, 0); 
             }
     else if (time > .7 && time < 3.2) {
            Shooter.setAngle(3.27); //Change this
            Drive.doManualDrive(0, 0, 0);
        }
        else if (time > 3.2 && time < 12) {
            Drive.doManualDrive(0, 0, 0);
            Shooter.controlWheel(true);
            Shooter.adjustAngle(0);
            if (time > 5. && time <12) {
             Drive.doManualDrive(0, 0, 0);
             Shooter.fire(subtime * 1.5 < 5);
            }
        }
        else if (time >= 12 && time < 13) {
            Shooter.adjustAngle(-1);
        }
     
        else if (time >= 13 && time <= 13.2){
            Shooter.adjustAngle(0);
            Drive.doManualDrive(180, .5, 0);
        } 
        else if (time >= 13.2 && time <= 13.8){
            Shooter.adjustAngle(0);
            Drive.doManualDrive(180, 1, 0);
        } 
        
         else if (time >= 13.8 && time <= 14 ){
            Shooter.adjustAngle(0);
            Drive.doManualDrive(180, 1, 0);
        } 
     
        
        else if (time >= 14 && time < 14.5) {
            //Shooter.adjustAngle(0);
           Shooter.setAngle(2.99);  //Update to actual value
            Drive.doManualDrive(.8, 80, 0);
        
    }
        else if (time >=14.5) {
            Drive.doManualDrive(0, 0, .9);
            Shooter.setAngle(2.99);  //Update to actual value
        }
     
        else {
            Shooter.adjustAngle(0);
            Drive.doManualDrive(0, 0, 0);
        }
     
   
    }

    public static void doNothing() {
        System.out.println("Doing Nothing");
    }
}
