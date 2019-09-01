/*
The algorithm used here was based on this paper:
http://www8.cs.umu.se/kurser/TDBD17/VT06/utdelat/Assignment%20Papers/Path%20Tracking%20for%20a%20Miniature%20Robot.pdf

link for the circular queue / buffer thingy for efficent searching & interpolation
https://www.geeksforgeeks.org/circular-queue-set-1-introduction-array-implementation/
*/
package frc.lib.math;

import frc.subsystems.Drive.AutoDriveSignal;

public class PurePursuit{

    double totalFinished; 
    double totalRemaining; 

    
    public PurePursuit(Path path, boolean isReversed){
        
    }

    /*
    ● Find the closest point
    ● Find the lookahead point
    ● Calculate the curvature of the arc to the lookahead point
    ● Calculate the target left and right wheel velocities
    ● Use a control loop to achieve the target left and right wheel velocities
    */
    public AutoDriveSignal calculate(Point currentPos){
        double x = currentPos.getX();
        double y = currentPos.getY();
        double theta = currentPos.getAngle();
        Point lookAheadPoint = new Point(0,0);// TODO
        double lookAheadDistance = 0; //TODO
        Point goalPoint = new Point(1,1); // TODO
        
        // converting coordinates of goal to robot coordinates (robot coordinates are @ origin)
        double goalX = (goalPoint.getX()-x) * Math.cos(theta) + (goalPoint.getY() - y) * Math.sin(theta);
        double goalY = - (goalPoint.getX() - x) *Math.sin(theta) + (goalPoint.getY() - y) * Math.cos(theta);
        double radius = lookAheadDistance * lookAheadDistance /(goalX*2);
        
        // calculate wheel velocities using Rate limiter & calculuated curvature (inverse of radius)
    
        return new AutoDriveSignal();
    }

    public boolean isFinished(){
        if(){

        }
    }
}