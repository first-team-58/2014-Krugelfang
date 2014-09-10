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
 * @author Patrick Libby
 */
public class Motors {
    public static final Victor RightRear = new Victor(1);
    public static final Victor RightFront = new Victor(2);
    public static final Victor LeftRear = new Victor(3);
    public static final Victor LeftFront = new Victor(4);
    public static final RobotDrive DriveBase = new RobotDrive(LeftFront,
            LeftRear, RightFront, RightRear);

    
    private static final DigitalOutput RightRearBrake = new DigitalOutput(2);
    public static void Init() 
    {
        // Do Robot Init things here, like set Solenoid directions and reverse
        //   any motors that need to be reversed
    }
    
    public static void Drive() 
    {
        // This will be called from teleopPeriodic and will make the robot go.
        // STAND CLEAR!
        double direction = Driverstation.driver.getDirectionDegrees();
        double magnitude = Driverstation.driver.getMagnitude();
        double rotation = Driverstation.driver.getRawAxis(4);
        
        //Make it goooooooooooooo!
        DriveBase.mecanumDrive_Polar(magnitude, direction, rotation);
        
        //Turn on brakes
        boolean brake = !(Driverstation.driver.getRawButton(8));
        RightRearBrake.set(brake);
    }
}
