/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import com.sun.squawk.io.BufferedReader;
import com.sun.squawk.platform.posix.natives.Socket;
import java.io.*;
import javax.microedition.io.*;

/**
 *
 * @author student
 */
public class RasberryPi {

    static SocketConnection piConnect;
    static InputStream piInput;
    static DataInputStream data;
    static BufferedReader in;

    public static void connection() {
        try {
            piConnect = (SocketConnection) Connector.open("socket://10.0.58.123:2048");
            piInput = piConnect.openDataInputStream();

        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }
    
    public static void request() {
        try {
            int count;
            while ((count = piInput.read()) !=-1){
                System.out.println(count);
            }
        }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

