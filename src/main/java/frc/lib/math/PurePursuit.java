/*
The algorithm used here was based on this paper:
http://www8.cs.umu.se/kurser/TDBD17/VT06/utdelat/Assignment%20Papers/Path%20Tracking%20for%20a%20Miniature%20Robot.pdf

1. Obtain current position of the vehicle
2. Find the goal point:
2.1. Calculate the point on the path closest to the vehicle (xc, yc)
2.2. Compute a certain look-ahead distance D
2.3. Obtain goal point by moving distance D up the path from point (xc,yc)
3. Transform goal point to vehicle coordinates
4. Compute desired curvature of the vehicle γ = 2Δx/D2
5. Move vehicle towards goal point with the desired curvature
6. Obtain new position and go to point 2 

link for the circular queue / buffer thingy for efficent searching & interpolation
https://www.geeksforgeeks.org/circular-queue-set-1-introduction-array-implementation/
*/
package frc.lib.math;

import frc.subsystems.Drive.AutoDriveSignal;

public class PurePursuit{

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
        Point lookAheadPoint = new Point(0,0);
        double lookAheadDistance = 0; //calculate distance 
        Point goalPoint = new Point(1,1); // get goal point 
        
        // converting coordinates of goal to robot coordinates (robot coordinates are @ origin)
        double goalX = (goalPoint.getX()-x) * Math.cos(theta) + (goalPoint.getY() - y) * Math.sin(theta);
        double goalY = - (goalPoint.getX() - x) *Math.sin(theta) + (goalPoint.getY() - y) * Math.cos(theta);
        double radius = lookAheadDistance * lookAheadDistance /(goalX*2);
        
        // calculate wheel velocities using Rate limiter & calculuated curvature (inverse of radius)
    
        return new AutoDriveSignal ();
    }
}