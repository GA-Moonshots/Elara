/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;



public class BackWheelDown extends Command {

  private boolean isDone = false;
  private boolean isRunning = false;
  private int count = 0;
  private double time = 1.0;

  public BackWheelDown() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.backWheel);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if (isDone) {
      isDone = false;
      isRunning = true;
      count = 0;
    }
    else if (isRunning) {
      isDone = true;
      isRunning = false;
    }
    else {
      isRunning = true;
      count = 0;
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (isRunning){
      Robot.backWheel.backMotor.set(-0.8);
    }
    else if (isDone){
      Robot.backWheel.backMotor.set(0.1);
      count ++;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isDone && count >= 30*time;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.backWheel.backMotor.set(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.backWheel.backMotor.set(0.0);
  }
}
