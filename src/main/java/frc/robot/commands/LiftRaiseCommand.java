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
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Elevator;
/**
 * Responding to motor control. Runs infinitely
 */
public class LiftRaiseCommand extends Command {

  private double target;
  private Elevator elevator = Robot.elevator;

  public LiftRaiseCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.elevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //Robot.drivymcDriveDriverson.drive.arcadeDrive(Robot.m_oi., target);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // if we triggered a setPoint
    elevator.elevatorMotor.set(0.25);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //return !OI.yButton.get();
    return !OI.gamePad3.getRawButton(RobotMap.yBUTTON_NUM);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    elevator.elevatorMotor.set(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.drivymcDriveDriverson.drive.arcadeDrive(0,0);
  }

}
