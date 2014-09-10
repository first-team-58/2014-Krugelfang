/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author Accalia
 */
public class Lights {
    // LED Madness goes here
    public static Relay bumperLED = new Relay(1); 
    
    
    public static void runbumperLED(boolean status) {
        if (status) {
            bumperLED.set(Relay.Value.kOn);
        }
        else {
            bumperLED.set(Relay.Value.kOff);
        }
    }
}
