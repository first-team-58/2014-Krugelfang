/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author seanmanning
 */

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Joystick;

public class Balance {
    //creates the voltage value for the inclinomiter
    private static AnalogChannel xIncline = new AnalogChannel(2);
    //get the button for the balance
    
    public static void Init(){
        xIncline.setAverageBits(12);
        xIncline.initAccumulator();
        xIncline.resetAccumulator();
    }
}
