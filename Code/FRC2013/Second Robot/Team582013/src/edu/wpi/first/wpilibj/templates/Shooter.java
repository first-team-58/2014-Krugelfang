/*
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
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
    private static Victor sMotor = new Victor(5); //Shooter motor
    private static Victor yMotor = new Victor(8);  //Angle Motor
    public static AnalogChannel deckAngle = new AnalogChannel(1);
    private static Relay fireR = new Relay(5), //Right side up frisbee piston
            fireU = new Relay(6);  //Upside Down frisbee piston
    private static Timer shotclock = new Timer();
    private static boolean running = false;
    private static Encoder wheelSpeed = new Encoder(2,3);
    private static PIDController wheel = new PIDController(0.01, 0, 0.01, wheelSpeed, sMotor);
    //Fire the bee

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
                System.out.println("Firing");
                if (Controls.operator.getRawButton(8)) {
                    fireU.set(Relay.Value.kForward);   //Fire piston
                } else {
                    fireR.set(Relay.Value.kForward);  //Fire piston
                }
            } else {
                fireR.set(Relay.Value.kOff);  //Retract pistons
                fireU.set(Relay.Value.kOff);  //Retract pistons
            }
        } else {
            fireR.set(Relay.Value.kOff);  //Retract pistons
            fireU.set(Relay.Value.kOff);  //Retract pistons
            running = false;
            shotclock.stop();
            shotclock.reset();
        }
    }
    
    //PID Fire
    public static void pidFire() {
        boolean ready = wheelControl();
        if (ready) {
            fireR.set(Relay.Value.kOn);
        }
        else 
            fireR.set(Relay.Value.kOff);
    }
    
    
    
    //Manually Aim The Deck

    public static void manualAim() {
        if (Controls.operator.getRawButton(5)) {
            setAngle(1.68);
            
        }
        
        else if (Controls.operator.getRawButton(3)) {
        setAngle(1.54);
        }
        else if (Controls.operator.getRawButton(4)) {
            setAngle(1.85);
        }
        
        else  {
            adjustAngle(0);
        }
        if (!Controls.operator.getRawButton(5)) {
            //System.out.println("Waiting");
            if (Controls.operator.getRawAxis(6) < -.75) { //DOWN
              //  System.out.println("Moving");
                adjustAngle(.5);
            } else if (Controls.operator.getRawAxis(6) > .75) { //UP
                adjustAngle(-.5);
              // System.out.println("Moving");
            } 
            else if (Controls.operator.getRawAxis(5) < -.75) { //Down
                adjustAngle(-1);
                //System.out.println("Moving");
            }
            else if (Controls.operator.getRawAxis(5) > .75) { //up
                adjustAngle(+1);
              //  System.out.println("Moving");
            }
            else {
                adjustAngle(0);
                //System.out.println("Not Moving");
            }
        }

        fire(Controls.operator.getRawButton(7) && Controls.operator.getRawButton(2));
        if (Controls.operator.getRawButton(2)) {
            sMotor.set(-1);
           
        } 
        //Reverse Button CHANGED
//        else if (Controls.operator.getRawButton(10) && deckAngle.getAverageVoltage() < 3.8) {
//            sMotor.set(1);
//        }
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
        //System.out.println("Motor:" + value);
        double angle = deckAngle.getAverageVoltage();
        System.out.println("A: " + angle);
        SmartDashboard.putDouble("Deck Angle", angle);
        if ((angle < 3.75 && value > 0) || //DOWN 
                (angle > 2.46 && value < 0)) { //UP
            yMotor.set(value);
       } 
       else {
            yMotor.set(0);
        }
      //  yMotor.set(value);
        
    }
    

    public static void setAngle(double value) {
        double angle = deckAngle.getAverageVoltage();
        double speed;
        double adiff = Math.abs(angle - value);
        if (adiff < 0.05) {
            speed = .1;
        } else if (adiff < 0.1) {
            speed = .3;
        } else if (adiff < 0.3) {
            speed = .5;
        } else {
            speed = .7;
        }
        if (value > angle) {
            speed *= -1;
        }
        if ((angle < 3.75 && speed > 0) || //DOWN 
                (angle > 2.46 && speed < 0)) { //UP
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
    public static boolean wheelControl() {
       wheel.enable();
       wheel.setSetpoint(5000);
      
       return wheel.onTarget();
    }
    
    public static void fireRealy() {
//        if (Controls.operator.getRawButton(7)){
//            System.out.println("Fireing Rightsideup");
//        fireR.set(Relay.Value.kOn);
//        }
//        else {
//              fireR.set(Relay.Value.kOff);
//            fireU.set(Relay.Value.kOff);
//        }
        if (Controls.operator.getRawButton(8)) {
            fireU.set(Relay.Value.kForward);
            fireR.set(Relay.Value.kForward);
            //System.out.println("Fireing Upsidedown");
        }
        else {
            fireR.set(Relay.Value.kOff);
            fireU.set(Relay.Value.kOff);
    }
    
    
}
}
