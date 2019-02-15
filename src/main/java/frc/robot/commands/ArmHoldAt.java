/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
/**
 * Responding to motor control. Runs infinitely
 */
public class ArmHoldAt extends Command {

  private int check = 0;
  private double holdPower = 0.0;
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

    // determine the difference between where I want to go and where I'm at
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


  private void adjustHoldPower(){
    // determine the difference between where I want to go and where I'm at
    double error = Robot.arm.holdAt - Robot.arm.armEncoder.get();

    if(Math.abs(error) > RobotMap.ANGLE_TOLERANCE * 5){
      check++;
      if(check > 10 || Math.abs(error) > 100){
        check = 0;
        if(error < 0) holdPower -= .001;
        else if(error > 0) holdPower += .001;
      }
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    adjustHoldPower();
    SmartDashboard.putNumber("HoldPower", holdPower);
    SmartDashboard.putNumber("holdAt", Robot.arm.holdAt);
    Robot.arm.holdAt = (int)(SmartDashboard.getNumber("holdAt", 200));
    Robot.arm.armMotor.set(holdPower);
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
