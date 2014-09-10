/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Relay;


/**
 *
 * @author student
 */
public class Hang {

    //Define the soloniods for haning
    private static Solenoid rHook = new Solenoid(1), //Retract Hooks
            eHook = new Solenoid(2);  //Extend Hooks
    private static DigitalInput leftLimit = new DigitalInput(3),
            rightLimit = new DigitalInput(4);
    
   private static Relay sound = new Relay(2);

    // TODO adjust controller buttons
    public static void doHang() {

        //TODO prevent accidental PUSH
        if (Controls.operator.getRawButton(9) &&! Controls.operator.getRawButton(8)){
            sound.set(Relay.Value.kOn);
        }
        if (Controls.operator.getRawButton(9) && Controls.driver.getRawButton(8)) {
            Shooter.endGame();
        }
        if (Controls.operator.getRawButton(3)) {  // && Shooter.deckAngle.getAverageVoltage() > 3.8
            rHook.set(true);
            eHook.set(false);
        } else {
            eHook.set(true);
            rHook.set(false);
            sound.set(Relay.Value.kOff);
        }

    }
    //Test Solenoids Leave in for Debugging UNTIL EVERYTHING WORKS 
//   public static void testClaw() {
//       if(Controls.operator.getRawButton(1)) {
//           claw.set(true);
//          System.out.println("1");
//       }
//       else if  (Controls.operator.getRawButton(2)) {
//           claw2.set(true);
//       System.out.println("2");
//   }
//       
//       else if (Controls.operator.getRawButton(3)) {
//           three.set(true);
//           System.out.println("3");
//       }
//       else if (Controls.operator.getRawButton(4)) {
//           four.set(true);
//           System.out.println("4");
//       }
//       
//       else {
//           three.set(false);
//           claw.set(false);
//           claw2.set(false);
//           four.set(false);
//       }
//   }
}
