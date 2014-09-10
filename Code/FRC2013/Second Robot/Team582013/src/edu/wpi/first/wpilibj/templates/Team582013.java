/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.io.IOException;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Team582013 extends IterativeRobot {
    Preferences prefs;
    double testval;
    private static Compressor _comp;
    static SendableChooser autoChooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        //No longer using visual targeting 
        //CalculateShot.connect();
        
//        _comp = new Compressor(1, 1);
        autoChooser = new SendableChooser();
        autoChooser.addObject("Do Nothing", new Integer(1));
        autoChooser.addObject("Left Side", new Integer(2));
        autoChooser.addObject("Middle", new Integer(3));
        autoChooser.addDefault("Right Side", new Integer(4));
        SmartDashboard.putData("Autonomous program", autoChooser);
    }

    /**
     * This function is called periodically during autonomous
     */
    
    
    public void autonomousPeriodic() {
        //CalculateShot.consumeData();
        //TODO: Add actual autonomous code here:
        //autonomous.doAuto();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
       // CalculateShot.consumeData();
        Drive.doDrive();
        //CalculateShot.processData(false, false);
        //Shooter.manualAim();
       // Shooter.fireRealy();
      // hang.doHang();
        //Pickup.bPickup();  //No longer picking up
        prefs = Preferences.getInstance();
        testval = prefs.getDouble("testval", 1);
       // System.out.println(testval);
        

    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    }

    public void autonomousInit() {
  //      _comp.start();
        autonomous.Reinit();
    }

    public void teleopInit() {
    //    _comp.start();
    }

    public void disabledInit() {
        System.out.println("Robot is disabled");
      //  _comp.stop();
    }

    public void disabledPeriodic() {
        CalculateShot.consumeData();
    }
}
