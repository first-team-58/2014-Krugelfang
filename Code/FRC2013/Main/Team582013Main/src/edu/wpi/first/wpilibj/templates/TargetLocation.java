/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import com.sun.squawk.io.BufferedReader;
import java.io.*;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;

/**
 *
 * @author Team58
 */
public class TargetLocation {
    static SocketConnection piConnect;
    static InputStream piInput;
    static DataInputStream data;
    static BufferedReader in;
   
    //Initate Intial Connection With Pi/Classmate
    public static void connection() {
        try {
          piConnect = (SocketConnection) Connector.open("scoket://10.0.58.102:2048");
          piInput = piConnect.openInputStream();
          InputStreamReader isr = new InputStreamReader(piInput);
          in = new BufferedReader(isr);
        }
        catch (IOException ex) {
            System.err.println("Could not connect:"+ex.getMessage());
        }
    }
    
    //Request the latetst target data
    
    public static String request() {
      String values = "-1 -1 -1\r\n";
        try {
            values = in.readLine();
        }
        catch (IOException ex){
            System.err.println("Error reading values: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error: "+ex.getMessage());
        }
      return values;
    }
    
    //Convert the raw target data to the x value
    public static int getPositonX(int spc, String rawData) {
        String xValueRaw = rawData.substring(spc+1, rawData.length());
        int xValue = Integer.parseInt(xValueRaw);
        return xValue;
    
}
    
    //Convert the raw target data to a y value
public static int getPositionY(int spc, 
        
        String rawData){

    String yValueRaw = rawData.substring(0,spc);
    int yValue = Integer.parseInt(yValueRaw);
    return yValue;
}    


//Close the server connection
    public static void closeConnection() {
        try{
        piConnect.close();
        piInput.close();
        data.close();
        in.close();
        }catch(Exception e){}
            
        }
    }

    


