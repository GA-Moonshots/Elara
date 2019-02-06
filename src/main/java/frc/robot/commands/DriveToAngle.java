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
 * Drive to an angle comand
 * 
 * @author Moonshots Software Team
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

  /**
   * Using target, requestedRotation
   * Calculates error
   * output is the opposite value of error
   * 
   * Formula:
   * output = ((0.4 / 180) * error) +- minimum power
   * Add minimum power if moving clockwise
   * Subtract minimum power if moving counterclockwise
   * This does work, plug a few values in if unsure, and solve on paper.
   * 
   * @return output of movement
   * @author Creds: Robert Hayek
   */
  private double notReallyPID() {
      // NOTE: Negative return values will increase the gyro's value
      double maxPowerAllowed = 0.7; // cap the power 
      double minPowerNeeded = 0.3; // added to output
      double output;
      // Calculate the error
      double error = -(drive.gyro.getAngle() - target);	
      //Calculate the output without minPowerNeeded
      output = (0.4 / 180) * error;
      //Clockwise Logic
      if (error > 0) {
        return output += minPowerNeeded;
      }
      //Counter-clockwise logic
      else {
        return output -= minPowerNeeded;
      }
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
    return Robot.drivymcDriveDriverson.leftSide.get() == 0
        && Math.abs(drive.gyro.getAngle() - target) < RobotMap.ANGLE_TOLERANCE;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    drive.drive.arcadeDrive(0, 0);
    check = 0;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.drivymcDriveDriverson.drive.arcadeDrive(0, 0);
  }
}
