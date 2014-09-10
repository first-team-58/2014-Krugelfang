/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import java.io.InputStreamReader;
import java.io.InputStream;
import javax.microedition.io.SocketConnection;
import com.sun.squawk.io.BufferedReader;
import java.io.IOException;
import javax.microedition.io.Connector;

/**
 *
 * @author Team58
 */
public class TargetServer {
    static boolean connected = false;
    static SocketConnection piConnect;
    static InputStream piInput;
    static InputStreamReader ins;
    static String values = "-1 -1 -1\r\n";

    public static void connection() {
        System.out.println("Connecting");
        try {
            piConnect = (SocketConnection) Connector.open("socket://10.0.58.102:2048", Connector.READ, true);
            piInput = piConnect.openDataInputStream();
            ins=new InputStreamReader(piInput);
            
            connected = true;
        } catch (Exception e) {
            System.out.println("Connection failed");
        }
        System.out.println("Connection done");
    }

    public static String request() {
        try {
            if (connected && piInput.available() > 12) {
                
                char[] buf = new char[12];
                ins.read(buf);
                values = String.valueOf(buf);
               
                
                // Skip this if not enough data is waiting for us. 
                // use previous values instead
                //values = in.readLine();
               
            }
        } catch (Exception e) {
            System.out.println("Read Error..."+e.getMessage());
        }

        return values;
    }

    public static void closeConnection() {
        try {
            piConnect.close();
        } catch (Exception e) {
        }
        try {
            piInput.close();
        } catch (Exception e) {
        }
        
        }
}

    

