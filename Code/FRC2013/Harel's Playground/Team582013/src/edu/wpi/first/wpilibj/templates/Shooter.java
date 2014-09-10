/*
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 * @author student
 */
public class Shooter {
 // Preferences prefs = Preferences.getInstance();
    private static Victor pMotor = new Victor(6); //pickup motor, only for testing, will be moved later.
    private static Victor sMotor = new Victor(5); //Shooter motor
    private static Victor yMotor = new Victor(8);  //Angle Motor
    public static AnalogChannel deckAngle = new AnalogChannel(1);
    private static Relay fireR = new Relay(8); //Right side up frisbee piston
            //fireU = new Solenoid(3);  //Upside Down frisbee piston
    private static Timer shotclock = new Timer();
    private static boolean running = false;
      public static  Preferences prefs;
    //Fire the bee

    public static void fire(boolean doit) {

                if (Controls.operator.getRawButton(8)) {
                    fireR.set(Relay.Value.kOff);  //Retract pistons
                } else {
                    fireR.set(Relay.Value.kOn);  //Fire piston
                }
    }
    //Manually Aim The Deck

    public static void manualAim() {
        
          if (Controls.operator.getRawButton(5)) {
             System.out.println("3.26 setpoint");
             setAngle(3.26);
          }
          
          if (Controls.operator.getRawButton(4)) {
             System.out.println("2.99 setpoint");
             setAngle(2.99);
          }          
        
        if (!Controls.operator.getRawButton(4) && !Controls.operator.getRawButton(5)) {
            if (Controls.operator.getRawAxis(6) < -.75) { //DOWN
                adjustAngle(.5);
            } else if (Controls.operator.getRawAxis(6) > .75) { //UP
                adjustAngle(-.5);
               
            } 
            else if (Controls.operator.getRawAxis(5) < -.75) { //Down
                adjustAngle(-1);
            }
            else if (Controls.operator.getRawAxis(5) > .75) { //up
                adjustAngle(+1);
            }
            else {
                adjustAngle(0);
            }
        }
        
      
        fire(Controls.operator.getRawButton(7) && Controls.operator.getRawButton(2));
        //Dashboard preferences       
        prefs = Preferences.getInstance();

         int wheelPercent = prefs.getInt("WheelSpeed", 50 );
         double wheelSpeed = -1*wheelPercent/100.0;
        if (Controls.operator.getRawButton(2)) {
            sMotor.set(wheelSpeed);
        } 
        //Reverse Button CHANGED
        else if (Controls.operator.getRawButton(10) && deckAngle.getAverageVoltage() < 3.8) {
            sMotor.set(1);
        }
        else {
            sMotor.set(0);
        }
        
        
        //if (Controls.driver.getRawButton(5) || Controls.driver.getRawButton(6)) {
          //  pMotor.set(-1);
        //} else {
          //  pMotor.set(0);
        //}

    }
//Control Angle Motor and account for the angle of the deck   

    public static void adjustAngle(double value) {
        double angle = deckAngle.getAverageVoltage();
        System.out.println("A: " + angle);
        SmartDashboard.putDouble("Deck Angle", angle);
        if ((angle < 3.47 && value > 0)) { //UP 
            yMotor.set(value);    
        } else {
            yMotor.set(0);
        }

    }

    public static void setAngle(double value) {
        double angle = deckAngle.getAverageVoltage();
        double speed;
        double adiff = Math.abs(angle - value);
        if (adiff < 0.05) {
            speed = .1;
        } else if (adiff < 0.1) {
            speed = .15;
        } else if (adiff < 0.3) {
            speed = .2;
        } else {
            speed = 1;
        }
        if (value < angle) {
            speed *= -1;
        }
        if (Controls.operator.getRawButton(5)) { 
                yMotor.set(1);
                System.out.println("up");
        } else if (Controls.operator.getRawButton(4)) {
            yMotor.set(-1);
            System.out.println("down");
        } else {
            yMotor.set(0);
            System.out.println("still");
        }

    }
//Drive the deck off the screw for end game

    public static void endGame() {
        yMotor.set(1);
    }

    //Get the angle of the deck
    public static double getAngle() {
        double bridgeAngle = deckAngle.getAverageVoltage();
        return bridgeAngle;
    }
    //For autonomous

    public static void controlWheel(boolean on) {
        if (on = true) {
            sMotor.set(-1);

        } else {
            sMotor.set(0);
        }
    }
}
