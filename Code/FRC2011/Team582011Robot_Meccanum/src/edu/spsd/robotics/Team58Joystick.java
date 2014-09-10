/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.spsd.robotics;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Team 58
 */
public class Team58Joystick extends Joystick
{
    public Team58Joystick(final int port)
    {
        super(port);
    }

    public double getRawAxis(final int axis)
    {
        double val = super.getRawAxis(axis);

        if(val > 0 && val < 0.15)
            return 0.0;

        if(val < 0 && val > -0.15)
            return 0.0;

        return val;
    }
}
