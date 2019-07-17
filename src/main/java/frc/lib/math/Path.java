package frc.lib.math;

import frc.subsystems.PositionTracker;
import java.util.ArrayList;

public class Path{

    private ArrayList<Segment> segments = null;
    private Translation2D lastPoint;
    private double exitAngle;
    private boolean isUsable = false;

    public Path(){
        lastPoint = PositionTracker.getPosition().getTranslation();
    }

    //(1 unit = 1 m)
    public void addPoints (Translation2D point) {
        segments.add(new Segment(lastPoint, point));
        lastPoint = point;
        isUsable = true; // after 1st point is added, the 
    }
        // adds points to segments
        // once 2+ points are added, isUsable becomes true
    public void setExitAngle(double angle){
        exitAngle = angle;
    }

    /**
     * 
     * @param index
     * index of the segment array to get index from
     * 
     * @return
     * returns Segment at the specified index 
     */
    public Segment getsegment(int index){
        Segment seg = null;
        if(isUsable && index < segments.size()){
            seg = segments.get(index);
        }
      return seg;
    }

    /**
     * 
     */
    public double getPercent(){
        return 0;
    }
}