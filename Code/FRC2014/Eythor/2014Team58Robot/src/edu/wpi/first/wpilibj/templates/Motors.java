/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author Accalia
 */
public class Motors {

    public static final Victor RightRear = new Victor(1);
    public static final Victor RightFront = new Victor(2);
    public static final Victor LeftRear = new Victor(3);
    public static final Victor Leftfront = new Victor(4);
    public static final RobotDrive Drivebase = new RobotDrive(Leftfront,
            LeftRear, RightFront, RightRear);
//Define Paddle Motor (LT : Left top - looking from the front)
    public static Victor PaddleLT = new Victor(5);
    public static Victor PaddleLB = new Victor(6);
    public static Victor PaddleRT = new Victor(7);
    public static Victor PaddleRB = new Victor(8);
    //Digital Outputs for Shooting Victors (Break Mode)
    public static DigitalOutput DLT = new DigitalOutput(1);
    public static DigitalOutput DLB = new DigitalOutput(2);
    public static DigitalOutput DRT = new DigitalOutput(3);
    public static DigitalOutput DRB = new DigitalOutput(4);

    //Define Collector Motor
    public static Relay Collect = new Relay(1);
    //Define SmartDashboard prefrences
    public static Preferences prefs = Preferences.getInstance();

    public static void init() {
        //TODO reverse motor if needed

    }

    public static void Drive() {
        double direction = Driverstation.driver.getDirectionDegrees();
        double magnitude = Driverstation.driver.getMagnitude();
        double rotation = Driverstation.driver.getRawAxis(4);

        //Dead Zone for "make it go" controller
        if (magnitude < .15) {
            magnitude = 0;

        }
        //Dead Zone for "make it turn" controller
        if (Math.abs(rotation) < .15) {
            rotation = 0;
        }
        Drivebase.mecanumDrive_Polar(magnitude, direction, rotation);
    }

    private static void MakePaddleArmGo(double speed) {
        PaddleLT.set(speed);
        PaddleLB.set(speed);
        PaddleRT.set(speed);
        PaddleRB.set(speed);
    }

    public static void Paddle() {
        //Get a Shooting Speed Value from the dashboard
        int speedI = prefs.getInt("ShooterSpeed", 100);
        //Convert Value to Percent
        double speed = speedI / 100.0;

        //Get a Slow paddle forward speed from dashboard
        int sspeedI = prefs.getInt("Forward Paddle Speed", 15);
        double sdpeed = sspeedI / 100.0;
        //Get a slow reverse paddle speed from dashboard
        //double sspeedr = .2;  //If no Smartdash
        int sspeedrI = prefs.getInt("Reverse Paddle Speed", 20);
        double sspeedr = sspeedrI / -100.0;

        //Shoot (speed defined by dashboard
        if (Driverstation.operator.getRawButton(6)) {
            MakePaddleArmGo(speed);
        } else if (Driverstation.operator.getRawButton(4)) {
            MakePaddleArmGo(sspeed);
        } else if (Driverstation.operator.getRawButton(1)) {
            MakePaddleArmGo(sspeedr);
        } else {
            MakePaddleArmGo(0);
        }

    }

    
    private static void SetBrakes(boolean set) {
        DLT.set(set);
        DLB.set(set);
        DRB.set(set);
        DRT.set(set);
    }
    public static void Collector() {
        //Collector bring in ball
        if (Driverstation.operator.getRawButton(5)) {
            Collect.set(Relay.Value.kForward);
            
        }//Collector release ball
        else if (Driverstation.operator.getRawButton(3)) {
            Collect.set(Relay.Value.kReverse);
        } //Off
        else {
            Collect.set(Relay.Value.kOff);
        }
        //Switch to brake mode
        SetBrakes(!Driverstation.operator.getRawButton(2));
    }
}