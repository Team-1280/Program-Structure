package frc.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.lib.Threaded;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.Constants;

public class Intake extends Threaded{

    Spark spark = new Spark(iD);

    private static final Intake instance = new Intake();

    public static Intake getInstance(){
        return instance;
    }

    public void setPower(double power){
       spark.set(power);
    }

    public void update(){

    }

}