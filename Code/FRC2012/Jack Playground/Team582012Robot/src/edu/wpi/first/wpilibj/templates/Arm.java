// @author RiotCrew
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;

public class Arm {

    private static Victor m_bridgeArm = new Victor(6),
            arm_1 = new Victor(9),
            arm_2 = new Victor(8),
            arm_Spare = new Victor(7),
            arm_Lift = new Victor(10);
    private static DigitalInput m_armLimitUp = new DigitalInput(11),
            m_armLimitDown = new DigitalInput(12),
            m_bridgeLimitUp = new DigitalInput(14),
            m_bridgeLimitDown = new DigitalInput(13);

    public static void DoArm() {
        RunBridgeArm();
        RunArmPivot();
        RunArmRollers();
    }

    public static void Init() {
    }

    public static void RunBridgeArm() {
        boolean deploy = Controls.operator.getRawButton(6) || Controls.operator.getRawButton(5) || Controls.controller.getRawButton(1);
        RunBridgeArm(deploy);
    }
    public static void RunBridgeArm(boolean deploy){
        if (deploy && m_bridgeLimitUp.get()) {

            
            m_bridgeArm.set(.5);
        } else if (!deploy && m_bridgeLimitDown.get()) {
            m_bridgeArm.set(-.5);
        } else {
            m_bridgeArm.set(0.0);
        }
    }
    public static void RunArmPivot() {
        double armMoveValue = 1.0;
        boolean up = Controls.operator.getRawButton(3), down = Controls.operator.getRawButton(4);
        if (up && m_armLimitUp.get()) {
            arm_Lift.set(armMoveValue);
        } else if (down && m_armLimitDown.get()) {
            arm_Lift.set(-armMoveValue);
        } else {
            arm_Lift.set(0);
        }
    }

    public static void RunArmUp(){
        if (m_armLimitDown.get()) {
            arm_Lift.set(-1);
        } else {
            arm_Lift.set(0);
        }
    }

    public static void RunArmRollers() {
        double speed = 1000.0;
        boolean directionOut = Math.abs(Controls.operator.getZ()) < .5,
                outer = Controls.operator.getRawButton(1),
                inner = Controls.operator.getRawButton(2);
        if (directionOut && outer) {
            arm_1.set(speed);
        } else if (!directionOut && outer) {
            arm_1.set(-speed);
        } else {
            arm_1.set(0);
        }

        if (directionOut && inner) {
            arm_2.set(speed);
        } else if (!directionOut && inner) {
            arm_2.set(-speed);
        } else {
            arm_2.set(0);
        }
    }
    
    public static void EjectBalls(boolean run){
        if (run) {
            arm_1.set(.9);
            arm_2.set(.9);
        } else {
            arm_1.set(0);
            arm_2.set(0);
        }
    }
}
