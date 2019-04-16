package frc.robot.Autonomous.Actions;

public abstract class Action{

    public abstract void start(double timeStamp);
    public abstract void stop(double timeStamp);
    public abstract void loop(double timeStamp);
    public abstract void failCondition(double timeStamp); // what to do in case of fail
    public abstract boolean isDone();
    public abstract boolean isOK();
    public abstract boolean isActive();
    public abstract String toString();

}