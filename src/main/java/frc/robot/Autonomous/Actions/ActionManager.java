package frc.robot.Autonomous.Actions;

import java.util.

public class ThreadedManager implements Runnable{
 

  private Vector<ThreadBoi> queueThreaded;
  private boolean isRunning;
  private boolean isPaused;
  private double lastTimeStamp;
  private ExecutorService executor;
 
   public ThreadedManager(){
      executor = Executors.newSingleThreadExecutor();
      isRunning = true;
      isPaused = true;
   }
 
 @Override 
 public void run(){
 
 }
 
  void addToQueue(ThreadBoi){
    queueThreaded.add(task);
  }
  
 public void pause(){
  isPaused = true;
 }
 
 public void unPause(){
  isPaused = false;
 }
 

   
}
