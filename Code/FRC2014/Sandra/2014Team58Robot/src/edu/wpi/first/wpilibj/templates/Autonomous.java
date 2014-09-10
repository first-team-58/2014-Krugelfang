/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author slovejoy
 */
public class Autonomous {

    //drives robot into scoring range
    public static Timer totalTime = new Timer();

    public static void startTime() {
        totalTime.stop();
        totalTime.reset();
        totalTime.start();
    }

    public static void driveToShoot() {

        double distancewall = Driverstation.getDistanceWall();
        if (distancewall > 100) {
            Motors.DriveBase.mecanumDrive_Polar(.3, 0, 0);
        } else {
            Motors.DriveBase.mecanumDrive_Polar(0, 0, 0);
        }
    }
}
