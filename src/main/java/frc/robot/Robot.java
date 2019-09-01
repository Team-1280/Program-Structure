/*
*/

package frc.robot;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.lib.*;
import frc.subsystems.*;

public class Robot extends TimedRobot {
  
  private ThreadedManager manager = new ThreadedManager();
  private ExecutorService executor = Executors.newFixedThreadPool(4);

  // subsystems
  private Drive drive = Drive.getInstance();
  private Elevator elevator = Elevator.getInstance();
  private Intake intake = Intake.getInstance();
  private PositionTracker tracker = PositionTracker.getInstance();

  @Override
  public void robotInit() {
    drive.calibrateGyro();
    
    manager.addToQueue(drive, executor);
    manager.addToQueue(elevator, executor);
    manager.addToQueue(intake, executor);
    manager.addToQueue(tracker, executor);

  }

  @Override
  public void robotPeriodic() {
    // any code that should run regardless of mode ex) UI update code
  }
  
  @Override
  public void autonomousInit() {
    
  }

  @Override
  public void autonomousPeriodic() {
    
  }

  @Override
  public void teleopInit() {
   
  }

  @Override
  public void teleopPeriodic() {
    
  }

  @Override
  public void testPeriodic() {
    // test diagnostic code to run before matches (ie. check sensors, camera streams, stationary mode)
  }
}
