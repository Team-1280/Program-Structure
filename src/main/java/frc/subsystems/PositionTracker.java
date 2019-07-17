package frc.subsystems;

import frc.lib.math.*;
import frc.lib.Threaded;

public class PositionTracker extends Threaded{
    
    public static Transation getPosition(){
        return new Transation(new Translation2D(0,0), new Rotation2D(0,0));
    }

    @Override 
    public void update(){
        // 
    }
}