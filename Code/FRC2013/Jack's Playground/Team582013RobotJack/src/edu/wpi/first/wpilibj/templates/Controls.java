package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationEnhancedIO;
import edu.wpi.first.wpilibj.Joystick;

public class Controls {
    public static Joystick controller = new Joystick(1);
    public static DriverStationEnhancedIO driverStation;
    public static void Init() {
        Controls.driverStation = driverStation;
    }
    public static double getAnalog(int channel){
        try{
            return driverStation.getAnalogIn(channel)/3.3;
            
        }catch (DriverStationEnhancedIO.EnhancedIOException e){
            System.out.println("Enhanced IO Error: "+e.getMessage());
            return 0;
        }
    } 
    public static boolean getDigital(int channel){
        try{
            return !driverStation.getDigital(channel);
            
        }catch (DriverStationEnhancedIO.EnhancedIOException e){
            System.out.println("Enhanced IO Error: "+e.getMessage());
            return true;
        }
    }
    public static boolean getButton(int number){
        return controller.getRawButton(number);
        
    } 
}
