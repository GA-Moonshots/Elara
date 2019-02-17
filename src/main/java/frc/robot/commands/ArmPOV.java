/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
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
    // CHECK FOR POV COMMANDS
    switch(OI.xbox.getPOV()){
      case -1:  Robot.arm.armMotor.set(0.0);
                break;
      case 0:   Robot.arm.armMotor.set(-0.5);
      case 45:  break;
      case 90:  break;
      case 135: break;
      case 180: Robot.arm.armMotor.set(0.5);
                break;
      case 225: break;
      case 270: break;
      case 315: break;
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
