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

    
    //Main shooting method
    public static void shoot() {
    String rawData = TargetLocation.request();  //Recieve raw data
    System.out.println(rawData);
    /**
    //Process raw data
    int spc = rawData.indexOf(" ");
    int xPosition = TargetLocation.getPositonX(spc, rawData);
    int yPosition = TargetLocation.getPositionY(spc, rawData);
   
    
    double yMid = 384.0;  //Mid point of x window
    double xMid = 640.0;  //Mid point of y window
    double yDiff = yPosition - yMid;
    double xDiff = xPosition - xMid;
        
    
    //Adjust shooter based on data
        if(yDiff <= (yMid-5)) {
        Shooter.adjustAngle(1);
        
        }
        else if (yDiff >= yMid+5) {
        Shooter.adjustAngle(-1);    
        
    }
        else {
            boolean yReady = true;
        }
    if(xDiff <= xMid-10) {
        Drive.doManualDrive(0, 0, .1);
    }
    else if(xDiff >= xMid+10) {
        Drive.doManualDrive(0,0, -.1);
    }
    
    else {
        boolean xReady = true;
    }
    
    //When both y and x are ready fire.
    if (xReady && yReady == true) {
        Shooter.fire();
        xReady = false;
        yReady = false;
    }
    
      */  
    
}
}
