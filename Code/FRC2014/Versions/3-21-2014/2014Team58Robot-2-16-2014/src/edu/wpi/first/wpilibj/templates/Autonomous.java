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
            if (elapTime > 1 && elapTime < 2.8) {
                Motors.setBrakesDrive(false);
                Motors.DriveBase.mecanumDrive_Polar(.8, 0, 0);
            } else if (elapTime > 2.8 && elapTime < 2.9) {
                Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);
            } else if (elapTime > 2.9 && elapTime < 3.4) {
                System.out.println("Shooting");
                Motors.MakePaddleArmGo(.95);
                Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);
            } else if (elapTime > 3.4 && elapTime < 3.5) {
                Motors.MakePaddleArmGo(0);
            } else if (elapTime > 3.5 && elapTime < 6) {
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
            if (elapTime > 2 && elapTime < 3.8) {
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
                //Collect
            } else if (elapTime > 4.5 && elapTime < 4.9) {
                Motors.setBrakesDrive(true);
                Motors.SetBrakes(false);
                Motors.Collect.set(Relay.Value.kReverse);
            } else if (elapTime >= 4.9 && elapTime < 7) {
                Motors.setBrakesDrive(false);
                Motors.SetBrakes(false);
                Motors.Collect.set(Relay.Value.kReverse);
            } else if (elapTime > 7 && elapTime < 7.8) {
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
                twoBallHot();
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
        //Changed drive dealy 3/30
            if (elapTime > 1 && elapTime < 2.8) {
                Motors.setBrakesDrive(false);
                Motors.DriveBase.mecanumDrive_Polar(.8, 0, 0);
            } else if (elapTime > 2.8 && elapTime < 2.9) {
                Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);
            } else if (elapTime > 2.9 && elapTime < 6.8) {
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

        if (elapTime < 1){  //Changed from .1 to account for slower goal flip
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
            if (elapTime > 2 && elapTime < 5) {
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
            } else if (elapTime > 8.0 && elapTime < 9) {
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

    public static void twoBallNoBrake() {
        double elapTime = totalTime.get();
        System.out.println("Time" + elapTime);
        
        //Kick
        if (elapTime > .3 && elapTime < .4) {
            Motors.SetBrakes(false);
            Motors.MakePaddleArmGo(-.1);
        }
        //Accleration
        else if (elapTime > .5 && elapTime < .7) {
            Motors.SetBrakes(false);
            Motors.setBrakesDrive(true);
            Motors.DriveBase.mecanumDrive_Polar(.3, 0, 0);

//Drive forward
        }         else if (elapTime >= .7 && elapTime < 1.8) {
             Motors.SetBrakes(false);
            Motors.setBrakesDrive(true);
            Motors.DriveBase.mecanumDrive_Polar(1, 0, 0);

            //Cut Power
        } else if(elapTime >= 1.8 && elapTime < 1.9) {
                    Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);
                    }
            //First shot
         else if (elapTime >= 1.9 && elapTime < 2.3) {
         Motors.SetBrakes(true);
            Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);
            Motors.MakePaddleArmGo(1);
       // }
        //else if (elapTime >= 2.0 && elapTime < 2.10) {
        //Motors.Collector.set(0);
        // Motors.DriveBase.mecanumDrive_Polar(.6, 0, 0);

            
        //Collect
         }else if (elapTime >= 2.3 && elapTime < 3.3) {
            Motors.Collector.set(1);
            Motors.setBrakesDrive(true);
            Motors.SetBrakes(true);
            Motors.MakePaddleArmGo(0);
            
        }
         //Capture
           else if (elapTime >= 3.3 && elapTime < 3.6) {
            Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);   
            Motors.setBrakesDrive(false);
            Motors.Collector.set(1);
            //Motors.MakePaddleArmGo(.4);
            
        }
           //Shot 2  Delta shot time =.7
           else if (elapTime >= 3.6 && elapTime < 4.3) {
               Motors.Collector.set(0);
               Motors.MakePaddleArmGo(1);
           }
        
        else {
            Motors.SetBrakes(true);
            Motors.MakePaddleArmGo(0);
            Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);
            Motors.Collector.set(0);
        }

    }

    public static void twoBallFivFive() {
        double elapTime = totalTime.get();
        System.out.println("Time" + elapTime);
        
        if (elapTime > .8 && elapTime < .9) {
            Motors.SetBrakes(false);
            Motors.MakePaddleArmGo(-.1);
        }
        
        else if (elapTime > 1 && elapTime < 1.2) {
            Motors.SetBrakes(false);
            Motors.setBrakesDrive(true);
            Motors.DriveBase.mecanumDrive_Polar(.3, 0, 0);


        }         else if (elapTime >= 1.2 && elapTime < 3.5) {
             Motors.SetBrakes(false);
            Motors.setBrakesDrive(true);
            Motors.DriveBase.mecanumDrive_Polar(.6, 0, 0);

        } else if (elapTime >= 3.5 && elapTime < 3.9) {
         Motors.SetBrakes(true);

            Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);
            Motors.MakePaddleArmGo(1);
        }else if (elapTime >= 4.8 && elapTime < 3.90) {
        Motors.Collector.set(0);
         Motors.DriveBase.mecanumDrive_Polar(.6, 0, 0);

            
        
         }else if (elapTime >= 4.00 && elapTime < 5.5) {
            Motors.Collector.set(1);
            Motors.setBrakesDrive(true);
            Motors.SetBrakes(true);
            Motors.MakePaddleArmGo(0);
            
        }
           else if (elapTime >= 5.5 && elapTime < 5.7) {
            Motors.setBrakesDrive(true);
            Motors.Collector.set(0);
            Motors.MakePaddleArmGo(.4);
            
        }
           else if (elapTime >= 5.8 && elapTime < 6.6) {
               Motors.MakePaddleArmGo(1);
           }
        
        else {
            Motors.SetBrakes(true);
            Motors.MakePaddleArmGo(0);
            Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);
            Motors.Collector.set(0);
        }

    } 
    
    public static double hotDelay = 0;
    //Two Ball With Hot Goal
    public static void twoBallHot() {
        double elapTime = totalTime.get();
        System.out.println("Time" + elapTime);
        
        //Kick
        if (elapTime > (.3 +hotDelay) && elapTime < (.4+hotDelay)) {
            Motors.SetBrakes(false);
            Motors.MakePaddleArmGo(-.1);
        }
        //Accleration
        else if (elapTime > (.5+hotDelay) && elapTime < (.7+hotDelay)) {
            Motors.SetBrakes(false);
            Motors.setBrakesDrive(true);
            Motors.DriveBase.mecanumDrive_Polar(.3, 0, 0);

//Drive forward
        }         else if (elapTime >= (.7+hotDelay) && elapTime < (1.8+hotDelay)) {
             Motors.SetBrakes(false);
            Motors.setBrakesDrive(true);
            Motors.DriveBase.mecanumDrive_Polar(1, 0, 0);

            //Cut Power
        } else if(elapTime >= (1.8+hotDelay) && elapTime < (1.9+hotDelay)) {
                    Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);
                    }
            //First shot
         else if (elapTime >= (1.9+hotDelay) && elapTime < (2.29+hotDelay)) {
         Motors.SetBrakes(true);
            Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);
            Motors.MakePaddleArmGo(1);
       // }
        //else if (elapTime >= 2.0 && elapTime < 2.10) {
        //Motors.Collector.set(0);
        // Motors.DriveBase.mecanumDrive_Polar(.6, 0, 0);

            
        //Collect
         }else if (elapTime >= (2.28+hotDelay) && elapTime < (3.4+hotDelay)) {
            Motors.Collector.set(1);
            Motors.setBrakesDrive(true);
            Motors.SetBrakes(true);
            Motors.MakePaddleArmGo(0);
            
        }
         //Capture
           else if (elapTime >= (3.4+hotDelay) && elapTime < (3.5+hotDelay)) {
            Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);   
            Motors.setBrakesDrive(false);
            Motors.Collector.set(1);
            //Motors.MakePaddleArmGo(.4);
            
        }
           //Shot 2  Delta shot time =.7
           else if (elapTime >= (3.5+hotDelay) && elapTime < (4.2+hotDelay)) {
               Motors.Collector.set(0);
               Motors.MakePaddleArmGo(1);
           }
        
        else {
            Motors.SetBrakes(true);
            Motors.MakePaddleArmGo(0);
            Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);
            Motors.Collector.set(0);
        }

    }
    
    
}
