/*
Thanks to Team 3476. I based this on there autonomous code
*/
package frc.auto;

import frc.lib.math.Point;
import frc.subsystems.*;

public abstract class TemplateAuto {
 
    /*
    Put all Subsystem objs here
    */

    int side;

    Drive drive = Drive.getInstance();
    Elevator elevator = Elevator.getInstance();
    Intake intake = Intake.getInstance(); 
    PositionTracker positionTracker = PositionTracker.getInstance();
    boolean killSwitch = false;
    
    public TemplateAuto(Point start, int side){
        positionTracker.setStart(start);
        this.side = side;
    }

    public Point here() {
        return (positionTracker.getOdometery());
    }

    public double direction(){
        return positionTracker.getOdometery().getAngle();
    }

    synchronized public void killSwitch() {
        killSwitch = true;
    }

    synchronized public void isDead(){
        killSwitch = false;
    }

    

}