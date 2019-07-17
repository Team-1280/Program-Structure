package frc.subsystems;

public class Drive{

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

    // creates static instance of class
    private static final Drive instance = new Drive();

    private static Drive getInstance(){
        return instance;
    }
    
}