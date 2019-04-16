// WE DONE BOIS LETS GO 

package frc.robot.Autonomous.Actions;

public class LastAction extends Action {

    public void start(double timeStamp){
        // lets go we are done
    }
    public void stop(double timeStamp){
        // never gonna stop 
    }
    public void loop(double timeStamp){
        // we chillin here
    }
    public void failCondition(double timeStamp){
        // this shouldn't fail
    }
    public boolean isDone(){
        return false;
    }
    public boolean isOK(){
        return true;
    }
    public boolean isActive(){
        return true;
    }
    public String toString(){
        return "LastAction";
    }
}