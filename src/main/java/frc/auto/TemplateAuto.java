/*
Thanks to Team 3476. I based this on there autonomous code
*/
package frc.auto;

import frc.lib.math.Translation2D;
import frc.lib.math.Rotation2D;

public abstract class TemplateAuto  {
 
    /*
    Put all Subsystem objs here
    */

    int side;

    boolean killSwitch = false;
    
    public TemplateAuto(Translation2D start, int side){

    }

    public Translation2D here() {
        return PositionTracker.getInstance().getOdometry().translationMat;
    }

    synchronized public void killSwitch() {
        killSwitch = true;
    }

    

}