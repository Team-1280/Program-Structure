package frc.subsystems;

import frc.lib.math.*;
import frc.lib.Threaded;
import frc.robot.Constants;

public class PositionTracker extends Threaded{

    
    private Drive drive = Drive.getInstance();

    // stores the last encoder values for calculations
    private double lastEncoderLeft;
    private double lastEncoderRight;
    private double lastGyro;

    private Point position;

    private static PositionTracker instance = new PositionTracker();

    public static PositionTracker getInstance(){
        return instance;
    }

    public PositionTracker(){
      position = new Point (instance.getOdometery().getX(), instance.getOdometery().getY());
      lastGyro = drive.getAngle();
    }
    
    public Point getOdometery(){
        return position;
    }

    @Override 
    public void update(){
        double angle = drive.getAngle();
        double deltaLeft = drive.getLeftEncoder() - lastEncoderLeft;
        double deltaRight = drive.getRightEncoder() - lastEncoderRight;
        double distance = Constants.DRIVE_ENCODER_TICKS_PER_INCH * (deltaRight + deltaLeft)/2;
        double deltaX = position.getX() + distance * Math.cos(angle);
        double deltaY =  position.getY() + distance*Math.sin(angle);

        position.translateBy(deltaX, deltaY);
        position.rotateBy(angle - lastGyro);

        lastGyro = angle;
    }
}