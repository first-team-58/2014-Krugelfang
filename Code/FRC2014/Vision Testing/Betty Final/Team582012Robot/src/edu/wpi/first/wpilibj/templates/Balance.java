///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package edu.wpi.first.wpilibj.templates;
//
///**
// *
// * @author seanmanning
// */
//import edu.wpi.first.wpilibj.AnalogChannel;
//
//public class Balance {
//    //creates the voltage value for the inclinomiter
//
//    private static AnalogChannel xIncline = new AnalogChannel(6);
//    //get the button for the balance
//
//    public static void Init() {
//        xIncline.setAverageBits(12);
//        xIncline.initAccumulator();
//        xIncline.resetAccumulator();
//
//
//    }
//
//    public static double getAngle() {
//        double sensitivity = 0.035;
//        double offset = 2.5;
//
//        double volts = xIncline.getAverageVoltage();
//        double angle = ((volts - offset) / sensitivity) * -1.0;
//
//        return angle;
//    }
//
//    public static void toggleBalance() {
//        if (Controls.controller.getRawButton(8)) {
//            doBalance();
//        }
//
//    }
//
//    public static void printAngle() {
//        //System.out.println(getAngle());
//    }
//
//    public static void doBalance() {
//
//        // System.out.println(getAngle());
//
//       
//        double motorSpeed = .05 * getAngle();
//        double moveValue = motorSpeed;
//        double rotateValue = 0;
//
//
//
////        if (getAngle() < -5) {
////            moveValue = -motorSpeed;
////            rotateValue = -motorSpeed;
////
////        } else if (getAngle() > 5) {
////            moveValue = motorSpeed;
////            rotateValue = motorSpeed;
////        } else {
////            moveValue = 0;
////            rotateValue = 0;
////        }
//
//
//
//        Drive.Do5WDDrive(rotateValue, moveValue);
//    }
//}
