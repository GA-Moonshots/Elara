/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */ 
public class Drive extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  // here's some motors
  public Jaguar leftMotor1;
  public Jaguar leftMotor2;
  public Jaguar rightMotor1;
  public Jaguar rightMotor2;

  // drive system
  public DifferentialDrive drive;

  // grouping the motors
  SpeedControllerGroup rightSide;
  SpeedControllerGroup leftSide;

  public Drive(int leftPort1, int leftPort2, int rightPort1, int rightPort2){
    // linking motors to ports
    leftMotor1 = new Jaguar(leftPort1);
    leftMotor2 = new Jaguar(leftPort2);
    rightMotor1 = new Jaguar(rightPort1);
    rightMotor2 = new Jaguar(rightPort2);
    
    // setting up the motor groups
    rightSide = new SpeedControllerGroup(rightMotor1, rightMotor2);
    leftSide = new SpeedControllerGroup(leftMotor1, leftMotor2);

    // making differential drive
    drive = new DifferentialDrive(rightSide, leftSide);

    // TODO: still gotta enable deadband elimination!
    // even though we did it in Robot.java
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
