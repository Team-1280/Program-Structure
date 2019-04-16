package frc.robot.Autonomous.Actions;

import java.util.LinkedList;
import frc.robot.tasks.Loop;

public class ActionManager{
 
  private LinkedList<Action> queue = new LinkedList<Action>();
  private int currentIndex;
  private boolean startOfAction;

    Loop loop = new Loop(){
        @Override
        public void onStart(double timeStamp){
            currentIndex = 0;
        }
        @Override
        public void onLoop(double timeStamp){
            Action action = queue.get(currentIndex);
            if(startOfAction){
               action.start(timeStamp);
            }
            else{
                if(action.isDone())
                   action.stop(timeStamp);
                   if(currentIndex + 1 < queue.size()){
                        currentIndex ++;
                   }
                   else{
                       // DO NOTHING BOIS WE GUCCI
                       System.out.println("you forgot to do a ");
                   }
                if(action.isActive()){
                    if(!action.isOK()){
                        // i don't feel so good
                        action.failCondition(timeStamp);
                    }
                    else{
                       //thank you, next
                       action.loop(timeStamp);
                    }
                }
                
            }
        }
        @Override
        public void onStop(double timeStamp){

        }

    };

    public synchronized void addToQueue(Action action){
        queue.add(action);
    }

    public Action getNext(){
        return queue.getLast();
    }

    public Action getCurrentName(){
        return queue.element();
    }
}