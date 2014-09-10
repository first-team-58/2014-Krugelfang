/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author student
 */
public class Shooter {
    private static Victor wMotor = new Victor(5),  //Shooting wheel motor
             xMotor = new Victor (6),  //Curve Motor
             yMotor = new Victor (7);  //Angle Motor
    
    private static Relay trigger = new Relay(1);
            
//Control Angle Motor    
public static void adjustAngle(double value) {
    yMotor.set(1*value);
}

//Trigger Pistion
public static void fire() {
   trigger.set(Relay.Value.kForward);
}
    

public static void shoot() {
if(Controls.driver.getRawButton(3)) {
    System.out.println("enabled");
    CalculateShot.shoot();
    
}

}
public static void manualAim() {
    if(Controls.operator.getRawButton(1)) {
        adjustAngle(-1);
        
    }
    else if (Controls.operator.getRawButton(3)) {
        adjustAngle(1);
    }
}
else if (Cot)
}

