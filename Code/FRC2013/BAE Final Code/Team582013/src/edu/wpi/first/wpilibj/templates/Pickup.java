/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author Team58
 */
public class Pickup {

    //Define relays for pickup
    private static Solenoid bLiftRaise = new Solenoid(5),
            bLiftRotate = new Solenoid(7),
            bLiftDump = new Solenoid(8),
            bLiftSuction = new Solenoid(6);
    //Timer
    private static Timer timer = new Timer();
    // Lift Toogle
    private static boolean liftRaise = false;

    public static void bPickup() {
        //Drop Suction Cup
        if (Controls.operator.getRawButton(1) && !liftRaise) {
            bLiftSuction.set(true);
        }
        else {
            bLiftSuction.set(false);
        }
        //Start Automated process
        //autoPickup();
        if(Controls.operator.getRawButton(4)){
        bLiftRaise.set(true);
        Shooter.setAngle(3);
        System.out.println("Lifting");
        } else {
           if(Controls.operator.getRawButton(3)){ 
               bLiftRaise.set(true);
           } else{
               bLiftRaise.set(false);
           }
           }
        timer.start();
        if(Controls.operator.getRawButton(3) && Controls.operator.getRawButton(4)){
            bLiftRotate.set(true);
            Shooter.setAngle(3);
            
          
        if (Controls.operator.getRawButton(6)) {
            bLiftDump.set(true);
        }
       // if(Controls.operator.getRawButton(3) && Controls.operator.getRawButton(4) && timer.get() > 2) {
            
           // bLiftDump.set(true);
            //timer.stop();
            //timer.reset();
                    
       // }
            
        } else {
            bLiftDump.set(false);
            bLiftRotate.set(false);
        }
    }
//Not used due to unpredictable pressure
    public static void autoPickup() {
        if (Controls.operator.getRawButton(4) && !liftRaise) {
            liftRaise = true;
            timer.start();
        }
        if (liftRaise) {
            double time = timer.get();
            Shooter.setAngle(3.12);
            if (time < 1) {
                System.out.println("Raising");
                bLiftRaise.set(true);
            } 
            else if (time < 1.5) {
                System.out.println("Turning");
                bLiftRotate.set(true);
            } 
            else if (time < 2) {
                System.out.println("Dumping");
                bLiftDump.set(true);
            }
            else if (time < 2.5) {
                System.out.println("Undumping");
                bLiftDump.set(false);
            }
            else if (time < 3) {
                System.out.println("Unrotating");
                bLiftRotate.set(false);
            }
            else if (time < 3.5) {
                System.out.println("Lowering");
                bLiftRaise.set(false);
            }
            else {
                System.out.println("DONE");
            //    timer.stop();
             //   timer.reset();
                liftRaise = false;   
            }
        }
    }
}
