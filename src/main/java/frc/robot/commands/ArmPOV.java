/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.OI;
import frc.robot.Robot;
/**
 * Responding to motor control. Runs infinitely
 */
public class ArmPOV extends Command {

  public ArmPOV() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.arm);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.arm.armMotor.set(0.0);
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(OI.xbox.getRawAxis(3) != 0)
    {
      Robot.arm.armMotor.set(.5*(OI.xbox.getRawAxis(3)));
    }
    else if(OI.xbox.getRawAxis(2) != 0)
    {
      Robot.arm.armMotor.set(0.75*-(OI.xbox.getRawAxis(2)));
    }
    else{    
      Robot.arm.armMotor.set(0);
      }
    }
        
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.arm.armMotor.set(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.arm.armMotor.set(0.0);
  }

}
