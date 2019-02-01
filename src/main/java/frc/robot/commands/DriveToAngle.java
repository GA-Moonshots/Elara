/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Drive;
/**
 * An example command.  You can replace me with your own command.
 */
public class DriveToAngle extends Command {

  private double target;
  private int check;
  private int requestedRotation;

  private Drive drive = Robot.drivymcDriveDriverson;

  public DriveToAngle(int requestedRotation) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivymcDriveDriverson);
    this.requestedRotation = requestedRotation;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    target = drive.gyro.getAngle() + requestedRotation;
    check = 0;
  }

  private double notReallyPID(){
    // NOTE: Negative return values will increase the gyro's value

    double maxPowerAllowed = 0.7; // cap the power 
    double minPowerNeeded = 0.3; // added to output
    if (requestedRotation > 0){
      double error = target - drive.gyro.getAngle(); // distance we have left to turn
      if(Math.abs(error) < RobotMap.ANGLE_TOLERANCE) check++; 
      if(check > 10){  // if we've been within tolerance for a while
          return 0;
      } else if(error > 0){  // we haven't overshot our target
        double proportion = error / requestedRotation;
        double output = maxPowerAllowed * proportion;
        if(output < minPowerNeeded) return -minPowerNeeded;
        else return -output; 
      }
      else {  // we've overshot our target and need to settle back in
        return minPowerNeeded;
      }
    }
    else{
        double error = drive.gyro.getAngle() - target; // distance we have left to turn
      if(Math.abs(error) < RobotMap.ANGLE_TOLERANCE) check++; 
      if(check > 10){  // if we've been within tolerance for a while
          return 0;
      } else if(error > 0){  // we haven't overshot our target
        double proportion = error / requestedRotation;
        double output = maxPowerAllowed * proportion;
        if(output < minPowerNeeded) return minPowerNeeded;
        else return output; 
      }
      else {  // we've overshot our target and need to settle back in
        return -minPowerNeeded;
      }
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // if we triggered a setPoint
    drive.drive.arcadeDrive(0, notReallyPID());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.drivymcDriveDriverson.leftSide.get() == 0 &&
     Math.abs(drive.gyro.getAngle() - target) < RobotMap.ANGLE_TOLERANCE;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    drive.drive.arcadeDrive(0,0);
    check = 0;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.drivymcDriveDriverson.drive.arcadeDrive(0,0);
  }

}
