/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.spsd.robotics;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Team 58
 */
public class Deployment
{
    private Relay tray;
    private Relay miniBot;

    private Timer deployTime;

    private boolean minibotHeld;

    RobotDrive driveSystem;

    RobotState state;

    public Deployment(RobotDrive md)
    {
        driveSystem = md;

        tray       = new Relay(4);
        tray.setDirection(Relay.Direction.kForward);

        miniBot    = new Relay(1);
        miniBot.setDirection(Relay.Direction.kForward);

        deployTime = new Timer();
        deployTime.reset();

        state = RobotState.getInstance();

        minibotHeld = true;
    }

    public void check()
    {
        Boolean deployButtonCurr = (Boolean)state.get("DIO_DEPLOY");

        if(deployButtonCurr.booleanValue() == false)
            return;
        else
        {
            if(deployTime.get() == 0)
            {
                // We start the deployment Cycle.
                tray.set(Relay.Value.kOn);
                //driveSystem.setLeftRightMotorOutputs(0.4, 0.42);
                deployTime.start();
            }
            else if(deployTime.get() > 0.3 && deployTime.get() <= 0.5)
            {
                if(minibotHeld == true)
                {
                    minibotHeld = false;
                    miniBot.set(Relay.Value.kOn);
                }
            }
            else if(deployTime.get() >= 2.0)
            {
                driveSystem.stopMotor();

                if(minibotHeld == false)
                {
                    minibotHeld = true;
                    miniBot.set(Relay.Value.kOff);
                }
            }
        }
    }
}
