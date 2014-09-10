/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.RobotDrive;
/**
 *
 * @author Accalia
 */
public class Motors {
    public static final Victor RightRear = new Victor(1);
    public static final Victor RightFront = new Victor(2);
    public static final Victor LeftRear = new Victor(3);
    public static final Victor LeftFront = new Victor(4);
    public static final RobotDrive DriveBase = new RobotDrive(LeftFront,    
            LeftRear, RightFront, RightRear);

    public static void Init() 
    {
        //Do Robot Init things here, like set Solenoid directions 
        // any reverse any mottrs that need to be reversed
    }
    
    public static void Drive() 
    {
        //This will be called from the TeleopPeriodic and will make the robot go
        //STAND CLEAR!
        double direction = Driverstation.driver.getDirectionDegrees();
        double magnitude = Driverstation.driver.getMagnitude();
        double rotation = Driverstation.driver.getRawAxis(4);
        
        //makes it go
        DriveBase.mecanumDrive_Polar(magnitude, direction, rotation);
        
        
    }
}