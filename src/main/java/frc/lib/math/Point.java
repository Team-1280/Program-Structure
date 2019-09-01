package frc.lib.math;

public class Point{

    double x, y;
    double theta;

    public Point(Point point){
        this.x = point.x;
        this.y = point.y;
        this.theta = point.theta;
    }
  
    public Point(double x, double y){
      this.x = x;
      this.y = y;
      theta = 0;
    }

    public Point(double x, double y, double theta){
        this.x = x;
        this.y = y;
        this.theta = theta;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getAngle(){
        return theta;
    }

    public void translateBy(double deltaX, double deltaY){
        x += deltaX;
        y += deltaY;
    }

    public void translateBy(Point pos){
        translateBy(pos.getX(), pos.getY());
    }

    public void rotateBy(double delta){
        theta += delta;
    }

    public void magnify(double product){
        x *= product;
        y *= product;
    }

    public double magnitude(){
        return Math.sqrt(Math.pow(x,2) + Math.pow(y, 2));
    }

    public void normalize(double k){
        x /= magnitude() * k;
        y /= magnitude() * k;
    }

    public Point inverse(){
        return new Point(-x,-y);
    }

    /**
     * 
     * @param start starting Point
     * @param end ending Point
     * @return distance between points
     */

    public static double distanceBetween(Point start, Point end){
        double deltaX = start.getX() - end.getX();
        double deltaY = start.getY() - end.getY();
        double distance = Math.sqrt(Math.pow(deltaX,2) + Math.pow(deltaY, 2));
        return distance;
    }

    public static double percentOfPath(Point start, Point end, Point currentPos){
        double totalDistance = distanceBetween(start, end);
        double distanceToEnd = distanceBetween(currentPos, end);
        double percent = distanceToEnd/totalDistance;
        if(percent > 1){
            System.out.println("Error! Robot Off course");
        }
        
        return percent;
    }

    /**
     * Function creates a copy of the point object
     * @return copy of Point object
     */
    public Point duplicate(){
        return new Point(x,y, theta);
    }

}