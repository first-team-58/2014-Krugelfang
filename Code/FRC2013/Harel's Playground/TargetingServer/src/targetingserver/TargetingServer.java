/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package targetingserver;
import java.net.*;
import java.io.*;
import java.util.Scanner;
/**
 *
 * @author student
 */
public class TargetingServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
      //  System.out.println("Press enter to continue");
       // Scanner keyboard = new Scanner(System.in);
       // keyboard.nextLine();
        
        ServerSocket cRio = null;
        try {
            cRio = new ServerSocket(2048);
        } catch (IOException e) {
            System.err.println("No data on 2048");
            System.exit(1);
        }
        
        Socket clientSocket = null;
        try{
            clientSocket = cRio.accept();
        }catch (IOException e) {
            System.err.println("Connection Failed");
            System.exit(2);
        }
        
        PrintWriter cRioOut = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader cRioIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String num1,num2, num3;
        double x1 =1;
        double y1 =3;
        double z1 = 2;
        
        num1 = cRioIn.readLine();
        System.out.println(num1);
        num2 = cRioIn.readLine();
        System.out.println("*"+num2);
        num3 = cRioIn.readLine();
        System.out.println("*"+num3);
        
        try
        {
            x1 = Double.parseDouble(num1);
            y1 = Double.parseDouble(num2);
            z1 = Double.parseDouble(num3);
       
                    
        }
        catch(NumberFormatException num)
        {
            System.out.println("Number has characters other than letters!");
            cRioOut.println("Number has characters other than letters!");


        }    
        cRioOut.println(String.valueOf(x1));
        cRioOut.println(String.valueOf(y1));
        cRioOut.println(String.valueOf(z1));
        System.out.println("Sucess");
        
        
        cRioOut.close();
        cRioIn.close();
        clientSocket.close();
        cRio.close();
        
        
        
    }
}
