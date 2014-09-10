package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationEnhancedIO;
import edu.wpi.first.wpilibj.Joystick;

public class Controls {
    public static Joystick controller = new Joystick(1);
    public static Joystick operator = new Joystick(2);
    public static DriverStationEnhancedIO driverStation;
    public static void Init(DriverStationEnhancedIO driverStation) {
        Controls.driverStation = driverStation;
    }
    public static boolean getButton(int number){
        return controller.getRawButton(number);
        
    } 
}
