// @author Team58
package edu.wpi.first.wpilibj.templates;
//import stuff
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Relay;

public class Drive {
    //name the jaguars

    private static Jaguar m_LFMotor = new Jaguar(1),
            m_RFMotor = new Jaguar(2),
            m_LRMotor = new Jaguar(3),
            m_RRMotor = new Jaguar(4),
            m_5WDMotor = new Jaguar(5);
    //name the breaks
   private static DigitalOutput m_LFBreak = new DigitalOutput(1),
            m_LRBreak = new DigitalOutput(2),
            m_RFBreak = new DigitalOutput(3),
            m_RRBreak = new DigitalOutput(4);
    //set motors for`obotdrive
    private static RobotDrive m_robotDrive = new RobotDrive(m_LFMotor, m_LRMotor, m_RFMotor, m_RRMotor);
    //name relay 1 piston
    private static Relay piston = new Relay(1);

    public static void Init() {
        piston.setDirection(Relay.Direction.kForward);
    }

    public static void DoDrive() {

        boolean goSlow = Controls.getButton(5) || Controls.getButton(6);
        //grab and name the z-axis(triggers) on the controller
     //   changed z axis to button
        boolean zAxis = Controls.controller.getRawButton(3);
        double zAxisValue = Controls.controller.getRawAxis(3);
        //boolean zAxis = false;
        if (zAxisValue < -.5 || zAxisValue > .5) {
         zAxis = true;

       }
        double Direction = Controls.controller.getDirectionDegrees();
        double Magnitude = Controls.controller.getMagnitude() * .80;
        double Rotation = Controls.controller.getRawAxis(4);

        if (goSlow) {
            Magnitude *= .5;
        }
        //test for left trigger and set speed of mecanums to 5th wheel speed
        if (zAxis) {
            piston.set(Relay.Value.kOn);

            if (Direction > -90 && Direction < 90) {
                Direction = 0.0;
            } else {
                Direction = 180.0;
            }
            if (Rotation > -.1 && Rotation < .1) {
                Rotation = 0.0;
            }
            double FiveWheelMag = Direction == 0.0 ? Magnitude : -Magnitude;
            if (Rotation != 0) {
                FiveWheelMag = 0.0;
            }

            //m_5WDMotor.set(FiveWheelMag);
            DoManualDrive(Direction, Magnitude * 0.79, Rotation * 0.79);
        } else {
            //m_5WDMotor.set(0.0);
            DoManualDrive(Direction, Magnitude, Rotation);
            piston.set(Relay.Value.kOff);
        }
    }

//set 5WDdrive to tankdrive mode and add sensitivity
    public static void Do5WDDrive(double moveValue, double rotateValue) {
        if (moveValue < .1) {
            moveValue = 0;
        }
        m_robotDrive.arcadeDrive(moveValue, rotateValue);
    }

    public static void DoManualDrive(double direction, double magnitude, double rotation) {

        if (Controls.controller.getRawButton(1)) {
            magnitude = magnitude * .5;
        }

        if (magnitude < .1) {
            magnitude = 0.0;
        }

        m_robotDrive.mecanumDrive_Polar(magnitude, direction, rotation);
    }
}
