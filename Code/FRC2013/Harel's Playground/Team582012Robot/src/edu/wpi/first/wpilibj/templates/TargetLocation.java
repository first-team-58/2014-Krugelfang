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
    
    public static void connection() {
        try {
          piConnect = (SocketConnection) Connector.open("scoket://10.0.58.193:2048");
          piInput = piConnect.openInputStream();
          InputStreamReader isr = new InputStreamReader(piInput);
          in = new BufferedReader(isr);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static String request() {
     String values = null;
        try {
           
            values = in.readLine();
            
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
      return values;
    }
    public static int getPositonX(int spc, String rawData) {
        String xValueRaw = rawData.substring(spc+1, rawData.length());
        int xValue = Integer.parseInt(xValueRaw);
        return xValue;
    
}
public static int getPositionY(int spc, String rawData){

    String yValueRaw = rawData.substring(0,spc);
    int yValue = Integer.parseInt(yValueRaw);
    return yValue;
}    


    public static void closeConnection() throws IOException {
        piConnect.close();
        piInput.close();
        data.close();
        in.close();
    }

    
}

