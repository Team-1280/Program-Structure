/*
The algorithm featured here is based on this article:
https://www.linearmotiontips.com/how-to-reduce-jerk-in-linear-motion-systems/
*/
package frc.lib.math;

import edu.wpi.first.wpilibj.Timer;

public class RateLimiter{

   private double lastTime; 
   private double lastValue;
   private double maxChange;

   /**
    * @param maxChange max amount output can change
    */
   public RateLimiter(double maxChange){
    lastTime = Timer.getFPGATimestamp();
    this.maxChange = maxChange;
   }

   public double getOutput(double value){
    double t = Timer.getFPGATimestamp() - lastTime;
    double change = constrain(value - lastValue, -maxChange*t, maxChange*t);
    lastTime = Timer.getFPGATimestamp();
    lastValue = value;
    return value + change;
   }

   public double constrain(double value, double min, double max){
    return Math.max(min, Math.min(value, max));     
   }

}