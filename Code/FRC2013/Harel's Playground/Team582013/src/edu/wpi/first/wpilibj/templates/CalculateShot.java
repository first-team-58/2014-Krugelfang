/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Team58
 */
public class CalculateShot {

  //  static Preferences prefs = Preferences.getInstance();
    public static boolean xCheck;
    public static boolean yCheck;

    public static void connect() {
        if (!TargetServer.connected) {
            TargetServer.connection();
        }
    }

    public static void consumeData() {
        TargetServer.request();
    }
    //Process Raw Targeting Data

    public static boolean processData(boolean xon, boolean yon) {
        //Convert The Raw String into integers x,y,z
        String rawData = TargetServer.request();
        int fspc = rawData.indexOf(" ");
        String xValue = rawData.substring(0, fspc);
        String newData = rawData.substring(fspc + 1, rawData.length());
        int y = Integer.parseInt(xValue);
        int sspc = newData.indexOf(" ");
        String yValue = newData.substring(0, sspc);
        int x = Integer.parseInt(yValue);

        SmartDashboard.putInt("Y location:", y);
        SmartDashboard.putInt("X Location:", x);
        //System.out.println ("X: "+ x+ " y: "+y);
        //When Trigger is pressed finalize X and Y and When Targeted Fire
        boolean yReady;
        if (Controls.operator.getRawButton(5) || xon) {
            Shooter.setAngle(3.27);
            yReady = true;
            
            //yReady = yTarget(y);   Old Targeting
            //System.out.println("turned on guys");
            //System.out.println("TargetingY: " + yReady);
        } else {
            yReady = checkTarget(x, y);
        }
        if (Controls.driver.getRawButton(1) || yon) {
    //        boolean xReady = xTarget(x);
            // boolean yReady = yTarget(y);
            //System.out.println("TargetingX: " + xReady);

        }
        return yReady;
    }

    //X targeting
//    public static boolean xTarget(int x) {
//      //  int xtrgt = prefs.getInt("xtrgt", 340);
//        //int xtrgttwo = prefs.getInt("xtrgttwo", 251);
//        int xMid = xtrgt;
//        if (Controls.operator.getRawButton(12)) {
//            xMid = xtrgttwo;
//        }
//        int xDiff = x - xMid;
//
//        //Move robot based on the target
//        //Returns True When Y is lined up
//        //TODO adjust the 15
//
//        //System.out.println("X: " + x + " DIFF: " + xDiff);
//
//        if (xDiff < -10) {
//            Drive.doManualDrive(0, 0, -.22);
//            return false;
//        } else if (xDiff > 10) {
//            Drive.doManualDrive(0, 0, .25);
//            return false;
//        } else {
//            Drive.doManualDrive(0, 0, 0);
//            return true;
//        }
//    }

    //X and Y True
    public static boolean checkTarget(int x, int y) {
        int yMid = 276;
        int xMid = 318;
        int yDiff = y - yMid;
        int xDiff = x - xMid;

        if (yDiff < 10 || yDiff > -10) {
            yCheck = true;
        } else {
            yCheck = false;
        }

        if (xDiff < 10 || xDiff > -10) {
            yCheck = true;
        } else {
            yCheck = false;


        }
        SmartDashboard.putBoolean("Y:", yCheck);
        SmartDashboard.putBoolean("X:", xCheck);
        return yCheck;
    }

    //Y targeting
//    public static boolean yTarget(int y) {
//        //int trgt;
//        //int trgttwo;
//        
//        //int trgttwo = prefs.getInt("trgttwo", 400);
//       // int trgt = prefs.getInt("trgt", 285);
//        //int trgtthree = prefs.getInt("trgtthree", 307);
//        //System.out.println(trgttwo);
//        //System.out.println(trgt);
//        //int yMid = 276;  //Point to target to
//       // int yMid = trgt;
//        if (Controls.operator.getRawButton(11)) {
//            yMid = trgtthree;
//        } else if (Controls.operator.getRawButton(12)) {
//            yMid = trgttwo;  //Ppint to target to for farther distances
//        }

//        int yDiff = y - yMid;
//
//        //Go up if target is below or up if target is done
//        //System.out.println("Y: " + y + " DIFF: " + yDiff);
//
//        double speed = 0;
//        int adiff = Math.abs(yDiff);
//        //System.out.println(adiff);
//        if (adiff < 10) {
//            // System.out.println("Speed: .15");
//            speed = .1;
//        } else if (adiff < 15) {
//            speed = .125;
//            //2
//            // System.out.println("Speed: .2");
//        } else if (adiff < 25) {
//            speed = .25;
//            //System.out.println("Speed: .25");
//        } else if (adiff < 40) {
//            speed = .3;
//        } else {
//            speed = .4;
//            //System.out.println("Speed: .6");
//        }
//
//        if (yDiff < -10) {
//            Shooter.adjustAngle(speed);
//            return false;
//        } else if (yDiff > 10) {
//            Shooter.adjustAngle(-1 * speed);
//            return false;
//        } else {
//            Shooter.adjustAngle(0);
//            return true;
//        }
    }
