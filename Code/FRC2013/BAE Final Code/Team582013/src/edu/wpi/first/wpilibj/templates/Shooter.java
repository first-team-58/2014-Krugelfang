/*
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
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

    private static Victor pMotor = new Victor(6); //pickup motor, only for testing, will be moved later.
    public static Victor sMotor = new Victor(5); //Shooter motor
   
    private static Victor yMotor = new Victor(8);  //Angle Motor
    public static AnalogChannel deckAngle = new AnalogChannel(1);
    private static Solenoid fireR = new Solenoid(4), //Right side up frisbee piston
            fireU = new Solenoid(3);  //Upside Down frisbee piston
    private static Timer shotclock = new Timer();
    private static boolean running = false;
    
    //PID controller
   public static PIDController deckControl = new PIDController (.1, .001, 0, deckAngle, yMotor);
    //Fire the bee
   public static  Preferences prefs = Preferences.getInstance();

    public static void fire(boolean doit) {
        if (doit) {
            boolean fire = false;
            if (!running) {
                shotclock.start();
                running = true;
                fire = true;
            } else {
                double time = shotclock.get();
                if (time >= 3) {
                    shotclock.reset();
                    fire = true;
                }
            }
            if (fire) {
                if (Controls.operator.getRawButton(8)) {
                    fireU.set(true);   //Fire piston
                } else {
                    fireR.set(true);  //Fire piston
                }
            } else {
                fireR.set(false);  //Retract pistons
                fireU.set(false);  //Retract pistons
            }
        } else {
            fireR.set(false);  //Retract pistons
            fireU.set(false);  //Retract pistons
            running = false;
            shotclock.stop();
            shotclock.reset();
        }
    }
    //Manually Aim The Deck

    public static void manualAim() {
        if (!Controls.operator.getRawButton(5)) {
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
         int wheelPercent = prefs.getInt("WheelSpeed", 50 );
         double wheelSpeed = -1*wheelPercent/100.0;
         System.out.println("Wheel Speed:" + wheelSpeed);
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
        
        
        if (Controls.driver.getRawButton(5) || Controls.driver.getRawButton(6)) {
            pMotor.set(-1);
        } else {
            pMotor.set(0);
        }

    }
//Control Angle Motor and account for the angle of the deck   

    public static void adjustAngle(double value) {
        
        double angle = deckAngle.getAverageVoltage();
        System.out.println("A: " + angle);
        SmartDashboard.putDouble("Deck Angle", angle);
        if ((angle > 2.53 && value < 0) || //DOWN 
                (angle < 3.61 && value > 0)) { //UP
            yMotor.set(value);
        } else {
            yMotor.set(0);
        }

    }
//Set Angle PID
    public static void setPIDAngle(double value) {
        deckControl.setSetpoint(value);
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
            speed = .5;
        }
        if (value < angle) {
            speed *= -1;
        }
        if ((angle > 2.35 && speed < 0) || //DOWN 
                (angle < 3.78 && speed > 0)) { //UP
            yMotor.set(speed);

        } else {

            yMotor.set(0);
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
