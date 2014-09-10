
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.*;
import edu.wpi.first.wpilibj.image.NIVision.MeasurementType;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

/**
 * Sample program to use NIVision to find rectangles in the scene that are illuminated
 * by a LED ring light (similar to the model from FIRSTChoice). The camera sensitivity
 * is set very low so as to only show light sources and remove any distracting parts
 * of the image.
 * 
 * The CriteriaCollection is the set of criteria that is used to filter the set of
 * rectangles that are detected. In this example we're looking for rectangles with
 * a minimum width of 30 pixels and maximum of 400 pixels.
 * 
 * The algorithm first does a color threshold operation that only takes objects in the
 * scene that have a bright green color component. Then a small object filter
 * removes small particles that might be caused by green reflection scattered from other 
 * parts of the scene. Finally all particles are scored on rectangularity, and aspect ratio,
 * to determine if they are a target.
 *
 * Look in the VisionImages directory inside the project that is created for the sample
 * images.
 */

public class Vision {
    
    public static void getInfo() {
    
            NetworkTable server = NetworkTable.getTable("SmartDashboard");

    try
    {
        System.out.println(server.getNumber("BLOB_COUNT", 0.0));
    }
    catch (TableKeyNotDefinedException ex)
    {
    }
}
    public static void doSomething() {
        NetworkTable server = NetworkTable.getTable("SmartDashboard");
            try
    {
        System.out.println(server.getNumber("BLOB_COUNT", 0.0));
        double blob = server.getNumber("BLOB_COUNT", 0.0);
        System.out.println(blob);
        if (blob >= 1) {
         Arm.EjectBalls(true);
        }
         else {
                 System.out.println("Nothing");
                 }
        
    } 
    
    
    catch (TableKeyNotDefinedException ex)
    {
    }
        
    } 
            
        
    
      
        
    }
    
    
