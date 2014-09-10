/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
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

    //adds the autonomous selection program to the SmartDashboard
    public static void autoSelectorInit() {
        autoSelect.addDefault("Do Nothing", new Integer(1));
        autoSelect.addDefault("Move Forward", new Integer(2));
        autoSelect.addDefault("Move Forward and Push", new Integer(3));
        autoSelect.addDefault("Move and Score", new Integer(4));
        autoSelect.addDefault("Move and Score 2 Balls", new Integer(5));
        SmartDashboard.putData("Autonomous Selected", autoSelect);
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
}
