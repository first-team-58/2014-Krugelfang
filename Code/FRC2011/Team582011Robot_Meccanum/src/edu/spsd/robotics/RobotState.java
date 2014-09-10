/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spsd.robotics;

import java.util.Hashtable;

/**
 *
 * @author Team 58
 */
public class RobotState
{
    private static final RobotState soleInstance = new RobotState();

    private Hashtable state = new Hashtable();

    public static RobotState getInstance()
    {
        return soleInstance;
    }

    private RobotState()
    {
        // enforce singularity
    }

    public void add(String key, Object obj)
    {
        state.put(key, obj);
    }

    public Object get(String key)
    {
        return state.get(key);
    }
}
