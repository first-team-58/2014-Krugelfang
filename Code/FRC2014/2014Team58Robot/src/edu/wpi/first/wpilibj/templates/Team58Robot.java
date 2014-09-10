/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Team58Robot extends IterativeRobot {

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        Driverstation.autoSelectorInit();
        Motors.init();
    }

    public void autonomousInit() {
        Autonomous.startTime();
    }
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
         Autonomous.driveToShoot();
    }

    /**
     * @override
     */
    public void teleopInit() {
        
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Motors.Drive();  //Comment this line to disable driving
        Motors.Paddle();  //Comment this line to disable shooting
        Motors.Collector();  //Comment this line to disable collecting

    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {

    }

    public void disabledInit() {

    }

}
