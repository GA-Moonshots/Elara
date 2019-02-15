/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
/**
 * Responding to motor control. Runs infinitely
 */
public class ArmHoldAt extends Command {

  private double holdPower = 0.2;
  private boolean stabalizationMode = false;

  public ArmHoldAt() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.arm);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }


  private double notReallyPID() {
  
    int STABALIZATION_RANGE = 100; // range at which point we fight to stabalize
    int TOTAL_RANGE = 900; // from low to high in encoder
    double MAX_POWER = 0.7; // cap the power 
    double MIN_UPWARD_POWER = 0.3; // fight gravity

    // determine the error
    double error = Robot.arm.holdAt - Robot.arm.armEncoder.get();

    // am I bouncing too high?
    if(stabalizationMode && error < STABALIZATION_RANGE){
      holdPower -= 0.01;
    }
    // am I bouncing too low?
    if(stabalizationMode && error > STABALIZATION_RANGE){
      holdPower += 0.01;
    }
    // IN RANGE AND NEED TO STABALIZE
    if(Math.abs(error) < STABALIZATION_RANGE){
      stabalizationMode = true;
      return holdPower;
    }

    // MOVE UP OR DOWN INTO RANGE
    // determine the power output neutral of direction
    double output = Math.abs(error / TOTAL_RANGE) * MAX_POWER;
    if(error > 0 && output < MIN_UPWARD_POWER) output = MIN_UPWARD_POWER;
    if(output > MAX_POWER) output = MAX_POWER;

    if(error > 0) return output;
    else return -output * 0.8; // nerf going down because gravity will help

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.arm.armMotor.set(notReallyPID());
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
