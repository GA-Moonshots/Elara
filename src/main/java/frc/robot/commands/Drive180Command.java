/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
/**
 * An example command.  You can replace me with your own command.
 */
public class Drive180Command extends Command {

  private double target;
  private DifferentialDrive drive = Robot.drivymcDriveDriverson.drive;

  public Drive180Command() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivymcDriveDriverson);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    target = Robot.drivymcDriveDriverson.gyro.getAngle() + 180;
    Robot.drivymcDriveDriverson.setSetpoint(target);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // if we triggered a setPoint
    
    drive.arcadeDrive(0, .25);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(Robot.drivymcDriveDriverson.gyro.getAngle() - target) < RobotMap.ANGLE_TOLERANCE;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivymcDriveDriverson.drive.arcadeDrive(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.drivymcDriveDriverson.drive.arcadeDrive(0,0);
  }

}
