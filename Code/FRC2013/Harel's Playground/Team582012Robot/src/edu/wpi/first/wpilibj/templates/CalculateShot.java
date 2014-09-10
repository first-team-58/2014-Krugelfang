/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Team58
 */
public class CalculateShot {
       
   private static boolean xReady = false;
    private static boolean yReady = false;

    public static void shoot() {
    String rawData = TargetLocation.request();
    int spc = rawData.indexOf(" ");
    double xPosition = TargetLocation.getPositonX(spc, rawData);
    double yPosition = TargetLocation.getPositionY(spc, rawData);
    double yMid = 384.0;
    double xMid = 640.0;
    double yDiff = yPosition - yMid;
    double xDiff = xPosition - xMid;
     System.out.println(yPosition);   
        if(yDiff <= (yMid-5)) {
        Shooter.adjustAngleUp(1);
        
        }
        else if (yDiff >= yMid+5) {
        Shooter.adjustAngleDwon(-1);    
        
    }
        else {
            boolean yReady = true;
        }
    if(xDiff <= xMid-10) {
        Drive.DoManualDrive(0, 0, .1);
    }
    else if(xDiff >= xMid+10) {
        Drive.DoManualDrive(0,0, -.1);
    }
    
    else {
        boolean xReady = true;
    }
    if (xReady && yReady == true) {
        Shooter.fire();
        xReady = false;
        yReady = false;
    }
    
        
    
}
}
