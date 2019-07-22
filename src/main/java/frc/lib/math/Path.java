package frc.lib.math;

import frc.subsystems.PositionTracker;

import java.util.ArrayList;

/*
points passed into Path Class
Path creates linear piecewise function
Points injected for better smoothing
Entire path smoothed for smoother turns 
Motion controller follows generated trajectory 
*/

public class Path{

    private ArrayList<Point> segments = null;
    private double exitAngle;
    PositionTracker position = PositionTracker.getInstance();

    public Path(){
        segments.add(position.getOdometery());
    }

    //(1 unit = 1 m)
    public void addPoints (Point point) {
        segments.add(point);
       
        
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
    public Point getPoint(int index){
        Point seg = null;
        if( index < segments.size()){
            seg = segments.get(index);
        }
      return seg;
    }

    /**
     * Function returns percent of path completed.
     * Used to track progress of auto mode and coordinate actions
     * 
     * @param index index of the starting point of the segment
     * 
     * @return percent of Path completed
     */
    public double getPercent(int index){
        return 0; // fix this
    }


    /**
     * Function Injects points and smooths points
     */
    public void modifyPath(double smoothness_weight){
        InjectPoints(6); // 6 inches of spacing in between points
        SmoothPath(1-smoothness_weight, smoothness_weight, 0.001);
    }

    private void InjectPoints(double spacing){
        
        // This list is gonna get pretty high if spacing is small
        ArrayList<Point> injectedList = new ArrayList<>(); 
        for(int i = 0; i < segments.size() - 1; i++){
            Point start = segments.get(i);
            Point end = segments.get(i+1);
            // calculating components of vector
            double deltaX = start.getX() - end.getX();
            double deltaY = start.getY() - end.getY();
            Point vector = new Point(deltaX, deltaY);
            // determining number of points to fit
            int numPoints = (int) Math.ceil(vector.magnitude()/spacing);
            vector.normalize(spacing); // normalizing vector to a certain magnitude (spacing)
            
            Point startPoint = new Point (start.getX(), start.getY()); 
            for(int j = 0; j < numPoints; j ++){
                start.translateBy(vector);
                injectedList.add(start);
            }
        }
        // adding last point of path onto list
        injectedList.add(segments.get(segments.size()-1));
        segments = injectedList;
    }

    /**
     * Function smooths points
     * 
     * @param weight_data 
     * @param weight_smooth
     * @param tolerance 
     */
    private void SmoothPath(double weight_data, double weight_smooth, double tolerance){
        ArrayList<Point> smoothSegments = makeCopy(segments);

        if(segments.size() > 1){
            double change = tolerance;
            while(change >= tolerance){
                change = 0.0;
                for(int i = 0; i < segments.size(); i++){
                    
                    /*
                    double aux = newPath[i][j];
					newPath[i][j] += weight_data * (path[i][j] - newPath[i][j]) + weight_smooth * (newPath[i-1][j] + newPath[i+1][j] - (2.0 * newPath[i][j]));
					change += Math.abs(aux - newPath[i][j]);	
                    */
                }
            }

        }
        else{
            System.out.println("Error - path too short to Inject");
            // log this
        }

       
    }

    /**
     * Function returns a non-reference copy of the arrayList
     * 
     * @param points
     * 
     * @return non-refernce copy of parameter arrayList
     */
    private ArrayList<Point> makeCopy(ArrayList<Point> points){
        ArrayList<Point> newPoints = new ArrayList<>();
        for(int x = 0; x < points.size(); x ++){
            Point point = points.get(x);
            newPoints.add(new Point(point.getX(), point.getY())); 
        }
        return newPoints;
    }

    

    
}