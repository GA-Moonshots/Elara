/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Robot;
/**
 * Responding to motor control. Runs infinitely
 */
public class KickstandToggle extends Command {

  public KickstandToggle() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.kickstand);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
      if (Robot.kickstandDown){
          Scheduler.getInstance().add(new KickstandUp());
          Robot.kickstandDown = false;

      }
      else{
        Scheduler.getInstance().add(new KickstandDown());
        Robot.kickstandDown = true;
      }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {

  }

}
