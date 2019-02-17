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
public class DriveCommand extends Command {

  private boolean notMoving = true;
  private boolean driveStraight = false;
  private double driveStraightAt;

  public DriveCommand() {
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

    //MANUAL DEAD ZONE
    double dead = 0.15;

    double valueleftx = OI.xbox.getRawAxis(0);
    double valuelefty = OI.xbox.getRawAxis(1);

    if(Math.abs(valueleftx) < dead){
      valueleftx = 0;
    }
    if(Math.abs(valuelefty) < dead){
      valuelefty = 0;
    }   

    // trigger assist driving straight 
    if(valuelefty > 0 && valueleftx == 0 && notMoving){
      notMoving = false;
      driveStraight = true;
      this.driveStraightAt = Robot.drivymcDriveDriverson.gyro.getAngle();
    }
    else if(valueleftx != 0){
      notMoving = false;
      driveStraight = false;
    }
    else if(valueleftx == 0 && valuelefty == 0){
      notMoving = true;
      driveStraight = false;
    }

    if(driveStraight){
      double difference = driveStraightAt - Robot.drivymcDriveDriverson.gyro.getAngle(); 
      Robot.drivymcDriveDriverson.drive.arcadeDrive(-valuelefty, (difference * .03)); 
    } else {
      Robot.drivymcDriveDriverson.drive.arcadeDrive(-valuelefty, -valueleftx); 
    }

    /*
    // CHECK FOR POV ANGLE COMMANDS
    if(OI.xbox.getPOV()==45){
      new DriveToAngle(45);
    } 
    else if(OI.xbox.getPOV()==90){
      new DriveToAngle(90);
    }
    else if(OI.xbox.getPOV()==135){
      new DriveToAngle(135);
    }
    else if(OI.xbox.getPOV()==180){
      new DriveToAngle(180);
    }
    else if(OI.xbox.getPOV()==225){
      new DriveToAngle(-135);
    }
    else if(OI.xbox.getPOV()==270){
      new DriveToAngle(-90);
    }
    else if(OI.xbox.getPOV()==315){
      new DriveToAngle(-45);
    }
    */
    
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
