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
public class DriveTank extends Command {


  public DriveTank() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivymcDriveDriverson);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //Robot.drivymcDriveDriverson.drive.arcadeDrive(Robot.m_oi., target);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    switch(OI.xbox.getPOV()){
      case -1:   break;
      // SAVED POSITION HIGH    
      case 0:   Scheduler.getInstance().add(new DriveSlow());
                break;
      case 45:  break;
      case 90:  ///Scheduler.getInstance().add(new DriveAdjustRight());
                break;
      case 135: break;
      // SAVED POSITION LOW
      case 180: //Scheduler.getInstance().add(new DriveToAngle(180));
                break;
      case 225: break;
      case 270: //Scheduler.getInstance().add(new DriveAdjustLeft());
                break;
      case 315: break;
    }

    //MANUAL DEAD ZONE
    double dead = 0.15;

    double valueleft = -OI.xbox.getRawAxis(1);
    double valueright = -OI.xbox.getRawAxis(5);

    if(Math.abs(valueleft) < dead){
      valueleft = 0;
    }
    if(Math.abs(valueright) < dead){
      valueright = 0;
    }   

    Robot.drivymcDriveDriverson.drive.tankDrive(valueright, valueleft);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
