/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Robotics
 */
public class Autonomous {

    //drives robot into scoring range
    public static Timer totalTime = new Timer();
    //  public static boolean hotGoal = true;
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
            if (elapTime < 1.8) {
                Motors.setBrakesDrive(false);
                Motors.DriveBase.mecanumDrive_Polar(.8, 0, 0);
            } else if (elapTime > 1.8 && elapTime < 1.9) {
                Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);
            } else if (elapTime > 1.9 && elapTime < 2.4) {
                System.out.println("Shooting");
                Motors.MakePaddleArmGo(.95);
                Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);
            } else if (elapTime > 2.4 && elapTime < 2.5) {
                Motors.MakePaddleArmGo(0);
            } else if (elapTime > 2.5 && elapTime < 5) {
                Motors.SetBrakes(true);

            } else {
                Motors.MakePaddleArmGo(0);
                //Motors.SetBrakes(false);
                Motors.setBrakesDrive(false);
                Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);

            }

        }
    }

    public static void twoBall() {
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
            if (elapTime> 2 && elapTime < 3.8) {
                Motors.setBrakesDrive(true);
                Motors.DriveBase.mecanumDrive_Polar(.6, 0, 0);
           //First Shot
            } else if (elapTime > 3.8 && elapTime < 3.9) {
                Motors.DriveBase.mecanumDrive_Polar(.4, 0, 0);
                Motors.MakePaddleArmGo(0);
                Motors.setBrakesDrive(true);
            } else if (elapTime > 3.9 && elapTime < 4.37) {
                Motors.DriveBase.mecanumDrive_Polar(.4, 0, 0);
                Motors.setBrakesDrive(true);
                System.out.println("Shooting");
                Motors.MakePaddleArmGo(.95);
                
            } else if (elapTime > 4.4 && elapTime < 4.5) {
                Motors.setBrakesDrive(true);
                Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);
                Motors.MakePaddleArmGo(0);
            } else if (elapTime > 4.5 && elapTime < 4.9) {
                Motors.setBrakesDrive(true);
                Motors.SetBrakes(false);
                Motors.Collect.set(Relay.Value.kReverse);
            } else if (elapTime >= 4.9 && elapTime < 7) {
                Motors.setBrakesDrive(false);
                Motors.SetBrakes(false);
                Motors.Collect.set(Relay.Value.kReverse);
            }
            else if (elapTime > 7 && elapTime < 7.8) {
                Motors.MakePaddleArmGo(.95);
                Motors.Collect.set(Relay.Value.kOff);
                Motors.SetBrakes(true);
            } else {
                Motors.MakePaddleArmGo(0);
                //Motors.SetBrakes(false);
                Motors.setBrakesDrive(false);
                Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);

            }

        }
    }

    public static void doAuto() {
        int selected = ((Integer) Driverstation.autoSelect.getSelected()).intValue();

        switch (selected) {
            case 1:
                System.out.println("1 ball");
                hotGoal();
                break;
            case 2:
                System.out.println("Two Ball");
                twoBallTest();
                break;
        }
    }

    public static void driveToShootDelay() {
        System.out.println("DELAY PROGRAM!");
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
            if (elapTime < 1.8) {
                Motors.setBrakesDrive(false);
                Motors.DriveBase.mecanumDrive_Polar(.8, 0, 0);
            } else if (elapTime > 1.8 && elapTime < 1.9) {
                Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);
            } else if (elapTime > 1.9 && elapTime < 6.8) {
                Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);
                System.out.println("Delay");
            } else if (elapTime > 6.8 && elapTime < 7.3) {
                System.out.println("Shooting");
                Motors.MakePaddleArmGo(.95);
                Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);
            } else if (elapTime > 7.3 && elapTime < 7.4) {
                Motors.MakePaddleArmGo(0);
            } else if (elapTime > 7.3 && elapTime < 10) {
                Motors.SetBrakes(true);
            } else {
                Motors.MakePaddleArmGo(0);
                //Motors.SetBrakes(false);
                Motors.setBrakesDrive(false);
                Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);

            }

        }
    }
    private static boolean hotGoal = true;
    public static void hotGoal() {
        double elapTime = totalTime.get();
      
        if (elapTime < .1) {
            hotGoal = Driverstation.hotGoal();
        }
        if (hotGoal) {
            System.out.println("going there");
            driveToShoot();
        } else {
            System.out.println("not there");
            driveToShootDelay();
        }

    }
    
    //Two Ball Auto Test
     public static void twoBallTest() {
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
            if (elapTime> 2 && elapTime < 5) {
                Motors.setBrakesDrive(true);
                Motors.DriveBase.mecanumDrive_Polar(.4, 0, 0);
           //First Shot
            } else if (elapTime > 5 && elapTime < 5.1) {
                Motors.DriveBase.mecanumDrive_Polar(.3, 0, 0);
                Motors.MakePaddleArmGo(0);
                Motors.setBrakesDrive(true);
            } else if (elapTime > 5.1 && elapTime < 5.58) {
                Motors.DriveBase.mecanumDrive_Polar(.4, 0, 0);
                Motors.setBrakesDrive(true);
                System.out.println("Shooting");
                Motors.MakePaddleArmGo(1);
                
            } else if (elapTime > 5.58 && elapTime < 5.6) {
                Motors.setBrakesDrive(true);
                Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);
                Motors.MakePaddleArmGo(0);
            } else if (elapTime > 5.6 && elapTime < 6.0) {
                Motors.setBrakesDrive(true);
                Motors.SetBrakes(false);
                Motors.Collect.set(Relay.Value.kReverse);
            } else if (elapTime >= 6.0 && elapTime < 8.0) {
                Motors.setBrakesDrive(false);
                Motors.SetBrakes(false);
                Motors.Collect.set(Relay.Value.kReverse);
            }
            else if (elapTime > 8.0 && elapTime < 9) {
                Motors.MakePaddleArmGo(1);
                Motors.Collect.set(Relay.Value.kOff);
                Motors.SetBrakes(true);
            } else {
                Motors.MakePaddleArmGo(0);
                Motors.SetBrakes(false);
                Motors.setBrakesDrive(true);
                Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);

            }

        }
    }
    
    
    
    
}
