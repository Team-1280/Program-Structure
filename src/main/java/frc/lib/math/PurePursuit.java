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
*/
package frc.lib.math;

public class PurePursuit{

    public PurePursuit(Path path, boolean isReversed){

    }

    public void Drive(){

    }
}