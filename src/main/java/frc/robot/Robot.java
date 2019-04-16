/*
*/

package frc.robot;

import frc.robot.tasks.*;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot {
  
  private Looper looperMan = new Looper();
  private CrashTracker tracker = new CrashTracker();
  
  @Override
  public void robotInit() {
    tracker.logRobotInit();
  }

  @Override
  public void robotPeriodic() {
    
  }
  
  @Override
  public void autonomousInit() {
    tracker.logAutoInit();
  }

  @Override
  public void autonomousPeriodic() {
    
  }

  @Override
  public void teleopInit() {
    tracker.logTeleopInit();
  }

  @Override
  public void teleopPeriodic() {
    
  }


  @Override
  public void testPeriodic() {

  }
}
