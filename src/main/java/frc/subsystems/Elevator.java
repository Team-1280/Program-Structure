package frc.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.lib.Threaded;
import frc.lib.drivers.LazyTalonSRX;
import frc.robot.Constants;

public class Elevator extends Threaded{

    LazyTalonSRX talon1 = new LazyTalonSRX(1);
    LazyTalonSRX talon2 = new LazyTalonSRX(2);

    private static final Elevator instance = new Elevator();

    public static Elevator getInstance(){
        return instance;
    }

    public void setPower(double power){
        talon1.set(ControlMode.PercentOutput, power);
        talon2.set(ControlMode.PercentOutput, power);
    }

    @Override
    public void update(){
        // 
    }

}