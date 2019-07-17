package frc.lib.math;

public class Segment{
    private Translation2D kStart, kEnd;
 

    double totalDistance;

    /** 
     * Constructor
     * 
     * @param start 
     * Location of robot at start of segment
     * 
     * @param end
     * Location of robot at end of segment
     * 
     */
    public Segment(Translation2D start, Translation2D end){
        kStart = start;
        kEnd = end;
    }

    /**
     * 
     * 
     * @return Percent completed of path
     */
    public double getPercent(){
        // (distance to finish from )
        // getPosition  
        return 0;
    }


    
}