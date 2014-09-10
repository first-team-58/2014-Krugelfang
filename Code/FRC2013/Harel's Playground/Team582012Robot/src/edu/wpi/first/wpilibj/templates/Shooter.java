/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author student
 */
public class Shooter {
    private static Victor wMotor = new Victor(5),  //Shooting wheel motor
             xMotor = new Victor (6),  //Curve Motor
             yMotor = new Victor (10);  //Angle Motor
    
    
      private static DigitalInput m_armLimitUp = new DigitalInput(11),
            m_armLimitDown = new DigitalInput(12),
            m_bridgeLimitUp = new DigitalInput(14),
            m_bridgeLimitDown = new DigitalInput(13);

    
    private static Relay trigger = new Relay(1);
            
public static void adjustAngleUp(double value) {
    if(!m_armLimitUp.get()){
        yMotor.set(0);
    }
    yMotor.set(1*value);
}

public static void adjustAngleDwon(double value) {
    if(!m_armLimitDown.get()){
        yMotor.set(0);
    }
    yMotor.set(1*value);
}

public static void fire() {
   trigger.set(Relay.Value.kForward);
}

public static void runShooter() {
    if (Controls.operator.getRawButton(3)) {
        CalculateShot.shoot();
    }
}
    
}






