/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

/**
 *
 * @author Accalia
 */
public class Driverstation {

    public static Joystick driver = new Joystick(1);
    public static Joystick operator = new Joystick(2);
    public static SendableChooser autoSelect = new SendableChooser();
    public static NetworkTable laptop = NetworkTable.getTable("SmartDashboard");

    //Adds the autonomous selection program to the SmartDashboard
    public static void autoSelectorInit() {
        autoSelect.addDefault("Shoot One Ball", new Integer(1));
        autoSelect.addObject("Shoot Two Balls", new Integer(2));
        SmartDashboard.putData("Autonomous Selector", autoSelect);
       //
       
    }
    
    public static void smartDashVariables() {
        SmartDashboard.putBoolean("Collector Status", Motors.collectorDashboard);
        SmartDashboard.putBoolean("In Range", Driverstation.shootingRange());
    }

    public static double getDistance() {
        try {
            double distance = laptop.getNumber("Distance");
            return distance;
        } catch (TableKeyNotDefinedException ex) {
            return -1.;
        }
    }

    public static double getDistanceWall() {

        try {
            double distancewall = laptop.getNumber("DistanceToWall");
            return distancewall;
        } catch (TableKeyNotDefinedException ex) {
            return -1;
        }
    }
    public static double getBlobCount() {
        try {
            double blobcount = laptop.getNumber("BLOB_COUNT");
          //  System.out.println(blobcount);
            return blobcount;
        }
        catch (TableKeyNotDefinedException ex) {
            return -1;
        }
    }
    public static boolean hotGoal(){
        double blobs = Driverstation.getBlobCount();
       // System.out.println(blobs);
        if (blobs >= 1) {
            return true;
        }
        else {
            return false;
        }
    } 
    
    //Calculate shooting range
    
    public static boolean shootingRange() {
        double distanceWall = Driverstation.getDistanceWall();  //get distnace from dashboard
        
        if (distanceWall > 80 && distanceWall < 120) {
            return true;
     
    }
        else {
            return false;
        }
    }
    
    //Test mode in smartdashboard
    public static void testModeInit() {
        //Drivebase
        LiveWindow.addActuator("Drivebase", "Right Rear", Motors.RightRear);
        LiveWindow.addActuator("Drivebase", "Left Rear", Motors.LeftRear);
        LiveWindow.addActuator("Drivebase", "Right Front", Motors.RightFront);
        LiveWindow.addActuator("Drivebase", "Left Front", Motors.LeftFront);
        //Shooter
        LiveWindow.addActuator("Shooter", "Left Bottom", Motors.PaddleLB);
        LiveWindow.addActuator("Shooter", "Left Top", Motors.PaddleLT);
        LiveWindow.addActuator("Shooter", "Right Bottom", Motors.PaddleRB);
        LiveWindow.addActuator("Shooter", "Right Top", Motors.PaddleRT);
        //Colelctor
        LiveWindow.addActuator("Collector", "Wheels", Motors.Collector);
        //Drive Brakes
        LiveWindow.addActuator("DriveBrakes", "Left Front", Motors.LeftFrontBrake);
        LiveWindow.addActuator("DriveBrakes", "Right Front", Motors.RightFrontBrake);
        LiveWindow.addActuator("DriveBrakes", "Right Rear", Motors.RightRearBrake);
        LiveWindow.addActuator("DriveBrakes", "Left Rear", Motors.LeftRearBrake);
        //Shooter Brakes
        LiveWindow.addActuator("Shooter Brakes", "Left Bottom", Motors.DLB);
        LiveWindow.addActuator("Shooter Brakes", "Right Bottom", Motors.DRB);
        LiveWindow.addActuator("Shooter Brakes", "Left Top", Motors.DLT);
        LiveWindow.addActuator("Shooter Brakes", "Right Top", Motors.DRT);
        






        



    }
        

}
