/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import java.io.IOException;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Team583013Robot extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    Compressor compress = new Compressor (1,1);

    public void robotInit() {
        
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousInit() {
    TargetLocation.connection();
    }
    
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    
    public void teleopInit() {
            TargetLocation.connection();

    }
    public void teleopPeriodic() {
        //Drive.doDrive();
      Shooter.shoot();
        System.out.println("On");
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
    public void disabledInt() throws IOException {
        TargetLocation.closeConnection();
        compress.stop();
    }
    
}
