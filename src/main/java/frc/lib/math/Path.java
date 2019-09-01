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

/*
To do: 
- Change velocity calculationsto Active pursuit
*/
public class Path{

    private ArrayList<Point> segments = null;
    private double exitAngle;
    private PositionTracker position = PositionTracker.getInstance();
    private  double[] distances;
    //private  double[] curvatures;
   // private  double[] velocities;

    public Path(Point here){
        segments.add(here);
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
    public void smoothPath(double smoothness_weight){
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
     * @param a weight_data 
     * @param b weight_smooth
     * @param tolerance 
     */
    private void SmoothPath(double a, double b, double tolerance){
        ArrayList<Point> smoothSegments = makeCopy(segments);

        if(segments.size() > 1){
            int numPoints = smoothSegments.size();
            double deltaPath = 0.0;
            while(deltaPath>= tolerance)
                {
                    for(int i = 1; i <  numPoints - 1; i++){
                    // getting values for calculuations
                    double oldX = segments.get(i).getX();
                    double x = smoothSegments.get(i).getX();
                    double aheadX  = smoothSegments.get(i+1).getX();
                    double behindX = smoothSegments.get(i-1).getX();
                    double oldY = segments.get(i).getY();
                    double y = smoothSegments.get(i).getY();
                    double aheadY = smoothSegments.get(i+1).getY();
                    double behindY = smoothSegments.get(i-1).getX();
                    // calculating amount to translate 
                    double deltaX = a * (oldX - x) + b * (aheadX + behindX - 2.0 * x);
                    double deltaY = a * (oldY - y) + b * (aheadY + behindY - 2.0 * y);
                    (smoothSegments.get(i)).translateBy(deltaX, deltaY);
                    deltaPath += Math.abs(oldX - smoothSegments.get(i).getX());
                }
            }
            segments = smoothSegments; 
        }
        else{
            System.out.println("Error - path too short to Inject");
            // log this
        }
    }

      /**
     * Adds distances between each point to an array - used for calculations later 
     */
    public void createDistances(){
        distances = new double[segments.size() - 1];
        double accum = 0;
        for(int i = 1; i <segments.size(); i++){
            distances[i-1] = accum + Point.distanceBetween(segments.get(i), segments.get(i-1));
            accum += distances[i-1];
        }
    }


    // more acurrate method: active pursuit 

    /**
     * Adds curvatures of each point to an array - used for calculations later 
     * The curvatures are calculated by taking 3 points at a time and fitting a circle through them.
     *  Then calculating the curvature of said circle 
     */
/*
    public void createCurvatures(){ 
        curvatures= new double[segments.size() - 1];
        for(int i = 1; i <segments.size(); i++){
            // taking 3 points
            double x1 = segments.get(i-1).getX();
            double x2 = segments.get(i).getX();
            double x3 = segments.get(i+1).getX();
            double y1 = segments.get(i-1).getY();
            double y2 = segments.get(i).getY();
            double y3 = segments.get(i+1).getY();

            if(x1 - x2 < 0.001) {
                 // ensure no divide by 0 error
                x2 += 0.001; 
            }
            double k1 = 0.5 * (Math.pow(x1, 2) + Math.pow(y1, 2)- Math.pow(x2, 2) - Math.pow(y2, 2))/ (x1-x2);
            double k2 = (y1 - y2 )/(x1- x2 );
            double b = 0.5 * (Math.pow(x2,2) - 2 * x2 * k1 + Math.pow(y2, 2) - Math.pow(x3 , 2) + 2 * x3 * k1 - Math.pow(y3,2)) / (x3* k2  - y3 + y2 - x2*k2 );
            double a = k1 - k2 * b; 
            double radius = Math.sqrt(Math.pow((x1 - a), 2) + Math.pow ( (y1 - b), 2));
            curvatures[i-1]  = 1/radius; // curvature = 1/r
        }
    }
*/

    public double getCurvature(Point position, Point nextPoint, Point lastPoint){
        double x1 = lastPoint.getX();
        double x2 = position.getX();
        double x3 = nextPoint.getX();
        double y1 = lastPoint.getY();
        double y2 = position.getY();
        double y3 = nextPoint.getY();

        if(x1 - x2 < 0.001) {
            // ensure no divide by 0 error
           x2 += 0.001; 
       }
       double k1 = 0.5 * (Math.pow(x1, 2) + Math.pow(y1, 2)- Math.pow(x2, 2) - Math.pow(y2, 2))/ (x1-x2);
       double k2 = (y1 - y2 )/(x1- x2 );
       double b = 0.5 * (Math.pow(x2,2) - 2 * x2 * k1 + Math.pow(y2, 2) - Math.pow(x3 , 2) + 2 * x3 * k1 - Math.pow(y3,2)) / (x3* k2  - y3 + y2 - x2*k2 );
       double a = k1 - k2 * b; 
       double radius = Math.sqrt(Math.pow((x1 - a), 2) + Math.pow ( (y1 - b), 2));
       return 1/radius; // curvature = 1/radius
    }

    /**
     * Adds max velocities @ each point to an array - used for pure pursuit algo for smoother turning
     */
    public void getVelocity(Point position, Point target, Point lastPoint, double velocity){
      
    }

    public double percentDone(double index, Point pos) {
        double percent = 0;
        double totalFinished;
        double totalRemaining;
        if(int x = 0; x < index; x++){
            
        }
        return percent;
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