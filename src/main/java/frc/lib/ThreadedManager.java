/*
Thanks to FRC Team 3476, which made the code that this class was based on.  
See their code here: https://github.com/FRC3476/FRC-2019
*/

package frc.lib;

import java.time.Duration;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class ThreadedManager implements Runnable{
 
  private Vector<Schedule> schedules;
  private boolean isRunning;
  private boolean isPaused;
  private double lastTimeStamp;
 
   public ThreadedManager(){
     
      isRunning = true;
      isPaused = true;
      ExecutorService executor = Executors.newSingleThreadExecutor();
      executor.execute(this);
   }
 
  @Override 
  public void run(){
      long waitTime = Duration.ofMillis(5).toNanos();
        while(isRunning){
          if(!isPaused){
              synchronized(this){
                for(Schedule task: schedules){
                  task.executeIfReady();
                 }
              }
           LockSupport.parkNanos(waitTime);
          }
      }
  }
 
  public void addToQueue(Threaded task, ExecutorService executor){
    schedules.add(new Schedule(task, executor));
  }
  
 public void pause(){
  isPaused = true;
 }
 
 public void unPause(){
  isPaused = false;
 }
  
 public void shutDown(){
  isRunning = false;
 }
 
private static class Schedule {
    Threaded task;
    public long taskTime;
    ExecutorService thread;

    private Schedule(Threaded task, ExecutorService thread) {
      this.task = task;
      this.thread = thread;
      taskTime = System.nanoTime();
    }

    public void executeIfReady() {
      
      if (task.getUpdateStatus()) {
        if (System.nanoTime() - taskTime > task.getPeriod()) {
          thread.submit(task);
          taskTime = System.nanoTime();
        }
      }
    }
}

   
}