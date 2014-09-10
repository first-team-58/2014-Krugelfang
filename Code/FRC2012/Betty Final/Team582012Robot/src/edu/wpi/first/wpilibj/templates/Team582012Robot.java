/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.AnalogChannel.*;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Team582012Robot extends IterativeRobot {

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    // The Compressor
    Compressor compress = new Compressor(1, 2);
    static SendableChooser autoChooser;
//AnalogChannel ultra = new AnalogChannel(2);
    public void robotInit() {
//        autoChooser = new SendableChooser();
//        autoChooser.addDefault("Coopertion", new Integer(1));
//        autoChooser.addObject("Coopertion 2", new Integer(2));
//        autoChooser.addObject("Coopertion 3", new Integer(3));
//        SmartDashboard.putData("Autonomous program", autoChooser);
        Controls.Init(m_ds.getEnhancedIO());
        Drive.Init();
        Hybrid.Init();
       // Balance.Init();
        Arm.Init();
        
    }

    public void execute() {
    }

    public void autonomousInit() {
        Hybrid.autoChoose();
        Hybrid.Reinit();
    }

    public void autonomousPeriodic() {
        Hybrid.DoHybrid();
    }

    public void teleopInit() {
        compress.start();
    }

    public void teleopPeriodic() {
        
        //double value = ultra.getAverageValue();
       // System.out.println(value);
        //SmartDashboard.putDouble(ERRORS_TO_DRIVERSTATION_PROP, 9);
        //double test = SmartDashboard.getDouble("Midpoint");
        //System.out.println(test);
        Drive.DoDrive();
        Arm.DoArm();
        //Vision.doSomething();
        //updateDashboard();
       // Arm.getAngle();
    }
    public void disabledPeriodic() {
        //updateDashboard();
    }

    public void disabledInit() {
        compress.stop();
    }
}
    

//    void updateDashboard() {
//        
//        Dashboard lowDashData = DriverStation.getInstance().getDashboardPackerLow();
//        lowDashData.addCluster();
//        {
//            lowDashData.addCluster();
//            {     //analog modules
//                lowDashData.addCluster();
//                {
//                    for (int i = 1; i <= 8; i++) {
//                        lowDashData.addFloat((float) AnalogModule.getInstance(1).getAverageVoltage(i));
//                    }
//                }
//                lowDashData.finalizeCluster();
//                lowDashData.addCluster();
//                {
//                    for (int i = 1; i <= 8; i++) {
//                        lowDashData.addFloat((float) AnalogModule.getInstance(2).getAverageVoltage(i));
//                    }
//                }
//                lowDashData.finalizeCluster();
//            }
//            lowDashData.finalizeCluster();
//
//            lowDashData.addCluster();
//            { //digital modules
//                lowDashData.addCluster();
//                {
//                    lowDashData.addCluster();
//                    {
//                        int module = 1;
//                        lowDashData.addByte(DigitalModule.getInstance(module).getRelayForward());
//                        lowDashData.addByte(DigitalModule.getInstance(module).getRelayForward());
//                        lowDashData.addShort(DigitalModule.getInstance(module).getAllDIO());
//                        lowDashData.addShort(DigitalModule.getInstance(module).getDIODirection());
//                        lowDashData.addCluster();
//                        {
//                            for (int i = 1; i <= 10; i++) {
//                                lowDashData.addByte((byte) DigitalModule.getInstance(module).getPWM(i));
//                            }
//                        }
//                        lowDashData.finalizeCluster();
//                    }
//                    lowDashData.finalizeCluster();
//                }
//                lowDashData.finalizeCluster();
//
//                lowDashData.addCluster();
//                {
//                    lowDashData.addCluster();
//                    {
//                        int module = 2;
//                        lowDashData.addByte(DigitalModule.getInstance(module).getRelayForward());
//                        lowDashData.addByte(DigitalModule.getInstance(module).getRelayReverse());
//                        lowDashData.addShort(DigitalModule.getInstance(module).getAllDIO());
//                        lowDashData.addShort(DigitalModule.getInstance(module).getDIODirection());
//                        lowDashData.addCluster();
//                        {
//                            for (int i = 1; i <= 10; i++) {
//                                lowDashData.addByte((byte) DigitalModule.getInstance(module).getPWM(i));
//                            }
//                        }
//                        lowDashData.finalizeCluster();
//                    }
//                    lowDashData.finalizeCluster();
//                }
//                lowDashData.finalizeCluster();
//
//            }
//            lowDashData.finalizeCluster();
//
//            lowDashData.addByte(Solenoid.getAllFromDefaultModule());
//        }
//        lowDashData.finalizeCluster();
//        lowDashData.commit();
//
//    }

