package frc.robot.Autonomous.Actions;
//

public abstract class ThreadedBoi implements Runnable{

    public boolean isUpdated = true;
    public boolean isPaused = false;
    public double updateTime = 0;
    
    @Override 
    public void run(){
        boolean snapPaused;
        synchronized(this){
            snapPaused = isPaused
        }
        if(!snapPaused){
            synchronized(this){
                isUpdated = false;
            }
            double start = Timer.getFPGATimeStamp();
            update();
              sychronized(this){
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
    

}
