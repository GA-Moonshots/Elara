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
 * 
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

  private double notReallyPID() {
    //////// NOTE: Negative return values will increase the gyro's value. //////
	  
    // Cap the power at `0.7`.
    double maxPowerAllowed = 0.7; 
    // Added to the output.
    double minPowerNeeded = 0.3; 
	////////////////////////////////////////////////////////
	// Make proportion global in the scoe of notReallyPID().
	double proportion;
	// Get the distance that we have left to turn.
	double error = target - drive.gyro.getAngle();
	////////////////////////////////////////////////////////
	if (Math.abs(error) < RobotMap.ANGLE_TOLERANCE) check++;
	// If we've been within tolerance for a while.
	if (check > 10) return 0;
	////////////////////////////////////////////////////////
	// Clockwise logic
	if (requestedRotation > 0 && error > 0) {
		proportion = error / requestedRotation;
		if ((maxPowerAllowed * proportion) < minPowerNeeded) return -minPowerNeeded;
		else return -(maxPowerAllowed * proportion);
	}
	// We've overshot our target and need to settle back.
	else if (requestedRotation > 0 && error < 0) return -minPowerNeeded;
	////////////////////////////////////////////////////////
	// Counter-Clockwise logic
	else if (requestedRotation <= 0 && error > 0) {
		proportion = error / Math.abs(requestedRotation);
		if ((maxPowerAllowed * proportion) < minPowerNeeded) return minPowerNeeded;
		else return maxPowerAllowed * proportion;
	}
	// We've overshot our target and need to settle back
	else return minPowerNeeded;
  }

  // Called repeatedly when this Command is scheduled to run.
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
