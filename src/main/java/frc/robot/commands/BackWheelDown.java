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

  private int count = 0;

  public BackWheelDown() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.backWheel);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    count = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (count<10) Robot.backWheel.backMotor.set(0.05);
    else if (count<20) Robot.backWheel.backMotor.set(0.1);
    else if (count<25) Robot.backWheel.backMotor.set(0.2);
    else if (count<30) Robot.backWheel.backMotor.set(0.5);
    else if (count<40) Robot.backWheel.backMotor.set(1.0);
    count ++;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
