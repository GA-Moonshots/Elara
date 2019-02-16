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
import frc.robot.subsystems.Arm;

/**
 * Drive to an angle comand
 * 
 * @author Moonshots Software Team
 */
public class ArmMoveTo extends Command {

  private double target;
  private int check;
  private int requestedEncoderValue;

  private Arm arm = Robot.arm;

  public ArmMoveTo(int requestedRotation) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.arm);
    this.requestedEncoderValue = requestedEncoderValue;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    target = arm.armEncoder.get() + requestedEncoderValue;
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
   */
  private double notReallyPID() {
    // NOTE: Negative return values will increase the gyro's value
    double MAX_POWER = 0.7; // cap the power 
    double MIN_POWER = 0.2; // lowest effective power
    int ENOUGH_CHECKS = 15; // how many times do we pass our target until we're satisfied?

    // determine the error
    double error = target - arm.armEncoder.get();

    // determine the power output neutral of direction
    double output = Math.abs(error / requestedEncoderValue) * MAX_POWER;
    if(output < MIN_POWER) output = MIN_POWER;
    if(output > MAX_POWER) output = MAX_POWER;

    // are we there yet? this is to avoid ping-ponging
    // plus we never stop the method unless our output is zero
    if(Math.abs(error) < RobotMap.ANGLE_TOLERANCE*5) check++;
    if(check > ENOUGH_CHECKS) return 0.0;

    // determine the direction
    // if I was trying to go a positive angle change from the start
    if(requestedEncoderValue > 0){
      if(error > 0) return -output; // move in a positive direction
      else return output; // compensate for over-turning by going a negative direction
    }
    // if I was trying to go a negative angle from the start
    else{
      if(error < 0) return output; // move in a negative direction as intended
      else return -output; // compensate for over-turning by moving a positive direction
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.arm.armMotor.get() == 0
        && Math.abs(arm.armEncoder.get() - target) < RobotMap.ANGLE_TOLERANCE*5;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.arm.armMotor.set(speed); = 0;
    check = 0;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.drivymcDriveDriverson.drive.arcadeDrive(0, 0);
  }
}
