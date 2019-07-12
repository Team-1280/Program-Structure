package frc.lib;

import edu.wpi.first.wpilibj.TimedRobot;

public abstract class Threaded implements Runnable{

    public long period;
    public boolean isUpdated = true;
    public boolean isPaused = false;
    public double updateTime = 0;
    
    @Override 
    public void run(){
        boolean snapPaused;
        synchronized(this){
            snapPaused = isPaused;
        }
        if(!snapPaused){
            synchronized(this){
                isUpdated = false;
            }
            double start = Timer.getFPGATimeStamp();
            update();
              synchronized(this){
                  updateTime = Timer.getFPGATimeStamp() - start;
            }
        }
    }
    
    synchronized public void unPause(){
        isPaused = false;
        isUpdated = true;
    }
     synchronized public void pause(){
        isPaused = true;
         isUpdated = false;
    }
    
     synchronized public boolean getPausedStatus(){
        return isPaused;
     }
    
    synchronized public boolean getUpdateStatus(){
        return isUpdated;
    }
    
    public abstract void update();
    
    public long getPeriod(){
        return period;
    }
    

}