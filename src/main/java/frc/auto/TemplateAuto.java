/*
Thanks to Team 3476. I based this on there autonomous code
*/
package frc.auto;

import frc.lib.math.Point;
import frc.subsystems.PositionTracker;

public abstract class TemplateAuto  {
 
    /*
    Put all Subsystem objs here
    */

    private int side;
    private PositionTracker positionTracker = PositionTracker.getInstance();
    private boolean killSwitch = false;
    
    public TemplateAuto(Point start, int side){

    }

    public Point here() {
        return (positionTracker.getOdometery());
    }

    synchronized public void killSwitch() {
        killSwitch = true;
    }

    

}