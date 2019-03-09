/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.concurrent.TimeUnit;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Elevator.ElevatorPosition;

/**
 * Responding to motor control. Runs infinitely
 */
public class DiscRelease extends Command {

  private Elevator elevator = Robot.elevator;
  private boolean started = false;
  private boolean done = false;

  public DiscRelease() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivymcDriveDriverson);
    requires(Robot.elevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //Robot.drivymcDriveDriverson.drive.arcadeDrive(Robot.m_oi., target);
    done = false;
    started = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    //MANUAL DEAD ZONE
    double dead = 0.1;

    double forwardPower = 0.5;

    // Check this out, Charlie...
    double startTime = 0.0;
    // if I haven't gotten started yet...
    if(!started) {
      // note the time I'm starting
      startTime = System.currentTimeMillis(); 
      // note that I've started
      started = true;
      // start the motor going
      elevator.elevatorMotor.set(0.2);
    }

    else if(started && System.currentTimeMillis() - startTime > 2500){
      // stop the next thing
      Robot.drivymcDriveDriverson.drive.arcadeDrive(0, 0);
      done = true;
    }
    // if I've started and the difference in time is over 
    else if(started && System.currentTimeMillis() - startTime > 500){
      // DANGER DANGER DANGER DANGER
      elevator.elevatorMotor.set(0);
      // start the next thing
      Robot.drivymcDriveDriverson.drive.arcadeDrive(0, forwardPower);
    }

    

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return done;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivymcDriveDriverson.drive.arcadeDrive(0,0);
    elevator.elevatorMotor.set(0);

    // ALSO CUT THE ELEVATOR POWER
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.drivymcDriveDriverson.drive.arcadeDrive(0,0);
    elevator.elevatorMotor.set(0);
    // ALSO CUT THE ELEVATOR POWER
  }

}
