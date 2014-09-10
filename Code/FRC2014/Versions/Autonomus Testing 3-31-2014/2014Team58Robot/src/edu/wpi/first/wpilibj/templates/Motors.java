/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Accalia
 */
public class Motors {

    //Define Drive Motors
    public static final Victor RightRear = new Victor(2);
    public static final Victor RightFront = new Victor(1);
    public static final Victor LeftRear = new Victor(3);
    public static final Victor LeftFront = new Victor(4);
    public static final RobotDrive DriveBase = new RobotDrive(LeftFront, LeftRear, RightFront, RightRear);
    // Define Paddle Motor (LT : Left top - looking from the front)
    public static Victor PaddleLT = new Victor(5);
    public static Victor PaddleLB = new Victor(6);
    public static Victor PaddleRT = new Victor(7);
    public static Victor PaddleRB = new Victor(8);
  //  public static Victor PaddleExtra1 = new Victor(9);
    public static Victor PaddleExtra2 = new Victor(10);
    //Digitial Outputs for Shooting Victors (Brake Mode) 
    public static DigitalOutput DLT = new DigitalOutput(1); //1
    public static DigitalOutput DLB = new DigitalOutput(2);  //2
    public static DigitalOutput DRT = new DigitalOutput(3);  //3
    public static DigitalOutput DRB = new DigitalOutput(4);   //4
    //Digital Outputs for Drive motors (Brake mode)
    public static DigitalOutput RightRearBrake = new DigitalOutput(5);
    public static DigitalOutput RightFrontBrake = new DigitalOutput(6);
    public static DigitalOutput LeftRearBrake = new DigitalOutput(7);
    public static DigitalOutput LeftFrontBrake = new DigitalOutput(8);
    public static DigitalOutput test = new DigitalOutput(9);
    //Timer
    public static final Timer shotTime = new Timer();
    //Total time
    public static double totalTime = -1;
    //Z axis status
    public static boolean zAxis = false;
    //Collector Toggle
    public static boolean collectorStatus = false;
    //Shooter motor status
    public static boolean shootMotorStatus = false;
    //Dashboard Status Collector
   public static boolean collectorDashboard = false;



        

    //Define collector motor changed to victor
   // public static Relay Collect = new Relay(1);
    //New Collector
    public static Victor Collector = new Victor(9);
    //Define smartdashboard preferences
    public static Preferences prefs = Preferences.getInstance();

    public static void init() {
        //TODO reverse motor if needed
    }

    public static void Drive() {
        //Obtain values for controller
        double direction = Driverstation.driver.getDirectionDegrees();
        double magnitude = Driverstation.driver.getMagnitude();
        double rotation = Driverstation.driver.getRawAxis(4);
        //Added for caost shot remove to disable
        //boolean coastShot = runCoastShot();
        //Dead zone for "drive" controller
        if (magnitude < .17) {
            magnitude = 0;
        }
        //Dead zone for "drive" controller
        if (Math.abs(rotation) < .22) {
            rotation = 0;
        }
        //Added for coast shot
        //Remove until if (Driverstation to disable)
//        if (coastShot) {
//            magnitude = 0;
//            direction = 0;
//            rotation = 0;
//        }
        //Added coastShot for the coast shot remove if there is a problem 4/1/2014 -harel
        if (Driverstation.driver.getRawButton(6)) {
            setBrakesDrive(true);
           //System.out.println("Brake");
        }
        if(Driverstation.driver.getRawButton(5)) {
            direction = direction + 180;
        }
        else {
            setBrakesDrive(false);
            //System.out.println("Coast");
        }
       // System.out.println("Magnitude" + magnitude);
        //System.out.println("Direction" + direction);
       // System.out.println("Rotation" + rotation);
        //Debug for drive
        DriveBase.mecanumDrive_Polar(magnitude, direction, rotation);
    }

    public static void MakePaddleArmGo(double speed) {
       // System.out.println("Speed:" + speed);
        PaddleLT.set(speed);
        PaddleLB.set(speed);
        PaddleRT.set(speed);
        PaddleRB.set(speed);
      //  PaddleExtra1.set(speed);
        PaddleExtra2.set(speed);
    }

    public static void Paddle() {
        //Get a shooting speed value from the dashboard
        //int speedI = prefs.getInt("ShooterSpeed", 100);
        //  Convert value to percent
        double speed = 1;

        //Get a slow paddle forward speed from dashboard
        //int sspeedI = prefs.getInt("Forward Paddle Speed", 15);
        double sspeed = .45;
        //Get a slow reverse paddle speed from dashboard
        //double sspeedr = .2;  //If no smartdash
        //int sspeedrI = prefs.getInt("Reverse Paddle Speed", 20);
        double sspeedr = -.2;
        
        double release =  -.4;

        //Shoot (speed defined by dashboard)
        if (Driverstation.operator.getRawButton(6)) {
            MakePaddleArmGo(speed);
           
        } //Adjust arm forward slowly
        else if (Driverstation.operator.getRawButton(1)) {
            MakePaddleArmGo(sspeed);
        } //Adjust arm backwards slowly
        else if (Driverstation.operator.getRawButton(4)) {
            MakePaddleArmGo(sspeedr);
        } // Default condition (not moving)
        //Relsease fast added at Boston
        else if (Driverstation.operator.getRawButton(2)) {
            MakePaddleArmGo(release);
        }
        else {
            MakePaddleArmGo(0);
        }
    }

    public static void SetBrakes(boolean set) {
        DLT.set(set);
        DLB.set(set);
        DRB.set(set);
        DRT.set(set);
    }
    //Drive train brake and coast mode
    public static void setBrakesDrive(boolean setdrive) {
        RightRearBrake.set(setdrive);
        LeftRearBrake.set(setdrive);
        LeftFrontBrake.set(setdrive);
        RightFrontBrake.set(setdrive);
        test.set(setdrive);
    }

    public static void Collector() {
        //Collector release in ball
//        if (Driverstation.operator.getRawButton(3)) {
//            Collect.set(Relay.Value.kForward);
//
//        } //Collector bring ball
//        else if (Driverstation.operator.getRawButton(5)) {
//            Collect.set(Relay.Value.kReverse);
//       } //Off
//        else {
//            Collect.set(Relay.Value.kOff);
//        }
        //Switch to brake mode
        SetBrakes(!Driverstation.operator.getRawButton(7));
    }
    
    public static void collectorToggle() {
        
        boolean buttonPressed = Driverstation.operator.getRawButton(5);
        if (buttonPressed && !collectorStatus) {
            shootMotorStatus = !shootMotorStatus;
            
        }
        if(shootMotorStatus) {
         //   Collect.set(Relay.Value.kReverse);  //If Relay
            Collector.set(1);
            collectorDashboard = true;
            //Added for lights 3/31/2014
            Lights.runbumperLED(collectorDashboard);
        }
        else {
         //   Collect.set(Relay.Value.kOff); //If Relay
            Collector.set(0);
            collectorDashboard = false;
            //Added for lights 3/31/2014
            Lights.runbumperLED(collectorDashboard);
        }
        collectorStatus = buttonPressed;
        //Added reverse
        if(Driverstation.operator.getRawButton(3)  && !collectorDashboard) {
           // Collect.set(Relay.Value.kForward);  If Relay
            Collector.set(-1);
            
        }
          
    }
    
    //Shooting on the move trigger
    public static boolean runCoastShot() {
        System.out.println("Coast Shot");
        //Check ZAxis and see if it was most recent pressed
        if (Driverstation.driver.getZ() < -.5) {
            if(!zAxis) {
                zAxis = true;
                totalTime = shotTime.get();
            }
            System.out.println("Running Coast");
            coastShot();
            return true;
        }
        else {
       zAxis = false; 
       return false;
    }
    
    }
    
    /** Run coast shot*/
    public static void coastShot(){
        //double time = shotTime.get()-totalTime;
        System.out.println("Running");
        // if (time < .1  && time < .8) {
            MakePaddleArmGo(1);
                    System.out.println("Shooting");

        }
        //else {
        //MakePaddleArmGo(0);

   // }
    
  //  }
    
    
    
    //Trick shot not in use as of 3-31-2014
    public static void runTrickShot(){
        if (Driverstation.operator.getZ() < -.5) {
            if(!zAxis) {
                zAxis = true;
                totalTime = shotTime.get();
            }
            brakeShot();
        }
        else {
       zAxis = false; 
    }
    }
    //Fade away shot
    //Not in use
    private static void brakeShot() {
        double time = shotTime.get()-totalTime;
        if (time <.5) {
            MakePaddleArmGo(1);
        }
        else if (time > .5 && time < .6) {
            MakePaddleArmGo(0);
        }
        else if (time > .6 && time < 1) {
            SetBrakes(false);
        }
        else {
            MakePaddleArmGo(0);
        }
    }
    
    //NOT in USE
    private static void trickShot() {
        double time = shotTime.get()-totalTime;
        System.out.println("Time:" + time);
        //Drive forward and set drive to coast
        if(time < .5) {
            DriveBase.mecanumDrive_Polar(-.8, 0, 0);
            setBrakesDrive(true);
            
        }
        //Start Shooting while coasting
        else if (time > .5 && time < 1 ) {
        MakePaddleArmGo(1);
        
    }
        
        //Put motors shooter motors into brake
        else if (time  > 1.15 && time < 1.3 ) {
            SetBrakes(false);
        }
        //Brakes for drive
        else if (time > 1.2 && time < 1.5){
            setBrakesDrive(false);
        }
        //Stop driving
        else {
          DriveBase.mecanumDrive_Polar(0, 0, 0);
          MakePaddleArmGo(0);
          setBrakesDrive(true);

        }
    
    }

    
   //Callibration for making motors drive straight in autonomous
    public static void driveStraight(double value) {
        double rValue;
        double lValue;
        //Account for sending 0 to the motor
        if(value == 0) {
           rValue = 0;
           lValue =0;
        }
        else{
          lValue = value;
          rValue = value - .04;  //Offset for driving straight   
        }
       
        
        //Left Motors
        Motors.LeftFront.set(lValue);  
        Motors.LeftRear.set(lValue);
        //Right Motors
        Motors.RightFront.set(rValue);
        Motors.RightRear.set(rValue);
}

}