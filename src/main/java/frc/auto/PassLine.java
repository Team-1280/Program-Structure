package frc.auto;

import frc.lib.math.*;

public class PassLine extends TemplateAuto implements Runnable{

    public PassLine(int side, double startX){
        super(new Point(startX, 0), side);
    }

    @Override 
    public void run(){
        Path p1 = new Path(here());
        p1.addPoints(new Point (1,1));
    }

    while(!){

    }

}