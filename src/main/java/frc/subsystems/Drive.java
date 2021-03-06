package frc.subsystems;

import frc.lib.Threaded;
import frc.lib.drivers.LazyTalonSRX;
import frc.robot.Constants;

public class Drive extends Threaded{

    /*
     2/3 Talons, 2/3 Victor SPXs 
      
     */

    public enum DriveState{
        TELEOP, PUREPURSUIT
    }

    // used for easily transfering drive data (1 obj vs multiple variables)
    public static class DriveSignal{
        /*
        units in inches per sec 
        */
        public double leftVel, rightVel, leftAcc,rightAcc;

        public DriveSignal(double leftVel, double rightVel){
            // calling other constructor
            this(leftVel, 0, rightVel, 0);
        }

        public DriveSignal(double leftVel, double leftAcc, double rightVel, double rightAcc){
            this.leftVel = leftVel;
            this.leftAcc = leftAcc;
            this.rightVel = rightVel;
            this.rightAcc = rightAcc;
        }
    }

    public static class AutoDriveSignal{
        DriveSignal signal;
        boolean isDone = false;

        public AutoDriveSignal(){

        }
        
        public AutoDriveSignal(DriveSignal signal, boolean isDone){
            this.signal = signal;
            this.isDone = isDone;
        }

        public void done(){
            isDone = true;
        }
    }

    // creates static instance of class
    private static final Drive instance = new Drive();

    public static Drive getInstance(){
        return instance;
    }

    public void calibrateGyro(){
        
    }

    /*
    Drive Train controllers 
    */

    public double getRightEncoder(){
        return 0; // replace w/ code to get encoder values 
    }

    public double getLeftEncoder(){
        return 0; // replace w/ code to get encoder values 
    }

    public double getAngle(){
        return 0; // returns gyro value
    }
    
    @Override 
    public void update(){

    }
    
}