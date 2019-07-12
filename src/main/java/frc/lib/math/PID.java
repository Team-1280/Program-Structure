package frc.lib;

/*
Thanks to: Team 254 & Team 955 
https://github.com/FRC-Team-955/Team955RobotLib/wiki/PIDF-Loop
https://github.com/Team254/FRC-2017-Public/blob/ab2b7a0c40a76d64e4be4f1c0471d8f2b3a4bcd4/src/com/team254/lib/util/control/SynchronousPIDF.java#L25
*/

import edu.wpi.first.wpilibj.Timer;

public class PID{

    private double kP;
    private double kI; // I in PID is usually overkill
    private double kD;
    private double kF = 0;

    private double upperBound;
    private double lowerBound;
    private double threshold;

    private double setPoint; 

    private double accumulator; 
    private double lastError;
    private double lastTime = -1;


    public PID(double P, double I, double D, double upper, double lower, double thresh){
        kP = P;
        kI = I;
        kD = D;
        upperBound = upper;
        lowerBound = lower;
        threshold = thresh;
        init();
    }

    public PID(double P, double I, double D, double upper, double lower, double thresh, double F){
        kP = P;
        kI = I;
        kD = D;
        kF = F;
        upperBound = upper;
        lowerBound = lower;
        threshold = thresh;
        init();
    }

    public void init(){
        accumulator = 0;
        lastError = 0;
    }
   
    // use if you want to re-initialize during operation 
    public void init(double value){

    }

    public double getValue(double currentValue){
       double error = Math.abs(currentValue-setPoint);
       double value = 0;
    
       if(Math.abs(error) < threshold){
            accumulator += error;
            if(isOnPoint(error)){
                accumulator = 0;
            }
            
            // Derivative value
            double dV = error - lastError;
            if(lastTime == -1)
                lastTime = Timer.getFPGATimestamp();
            double dt = Timer.getFPGATimestamp() - lastTime;
            lastTime = Timer.getFPGATimestamp();

            value = kP * error + kI * accumulator + kD * dV/dt + kF * setPoint;
       }
       else{
           accumulator = 0;
       }
        return value;
    }

    public void setSetPoint(double point){
        if(! (point < lowerBound || point > upperBound)){
            setPoint = point;
        }
    }

    public boolean isOnPoint(double value){
        boolean isOn = false;
        if(Math.abs(value-setPoint) <= threshold)
            isOn = true;
        return isOn;
    }

}