/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author student
 */
public class hang {

    //Define the soloniods for haning
    private static Relay rHook = new Relay(7), //Retract Hooks
            eHook = new Relay(2);  //Extend Hooks
//    private static DigitalInput leftLimit = new DigitalInput(3),
//            rightLimit = new DigitalInput(4);
    public static Timer hangTime = new Timer();

    // TODO adjust controller buttons
    public static void doHang() {

        //TODO prevent accidental PUSH
        if (Controls.operator.getRawButton(9) && Controls.driver.getRawButton(8)) {
            Shooter.endGame();
        }
        if (Controls.operator.getRawButton(10)) {
            rHook.set(Relay.Value.kOff);
                eHook.set(Relay.Value.kForward);
            
            
            
        } else {
     
            rHook.set(Relay.Value.kForward);
         
                eHook.set(Relay.Value.kOff);
              
             
        }

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

