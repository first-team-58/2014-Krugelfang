/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.DigitalOutput;
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
    
    public static final DigitalOutput RightRearBrake = new DigitalOutput (2);
    public static void Init()
    {
        
    
    
    }
    
    public static void Drive(){
        double direction = Driverstation.driver.getDirectionDegrees();
        double magnitude = Driverstation.driver.getDirectionDegrees();
        double rotation = Driverstation.driver.getDirectionDegrees();
        
        Drivebase.mecanumDrive_Polar(magnitude, direction, rotation);
        
        boolean brake = ! (Driverstation)
    }
}