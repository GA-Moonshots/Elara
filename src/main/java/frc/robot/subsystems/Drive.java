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
import frc.robot.RobotMap;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.DriveSmooth;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;


/**
 * * Instantiates the drive system with a gyro
 */
public class Drive extends Subsystem {
  
  // here's a gyro
  public ADXRS450_Gyro gyro = new ADXRS450_Gyro();

  // here's some motors
  public Jaguar leftMotor1;
  public Jaguar leftMotor2;
  public Jaguar rightMotor1;
  public Jaguar rightMotor2;

  // drive system
  public DifferentialDrive drive;

  // grouping the motors
  public SpeedControllerGroup rightSide;
  public SpeedControllerGroup leftSide;

  public double pidTune;

  public Drive(){    
    // PID STUFF: https://frc-pdr.readthedocs.io/en/latest/control/using_WPILIB's_pid_controller.html#implementing-a-basic-pid-control

    gyro.reset();
    // linking motors to ports
    leftMotor1 = new Jaguar(RobotMap.LEFT1PORT);
    leftMotor2 = new Jaguar(RobotMap.LEFT2PORT);
    rightMotor1 = new Jaguar(RobotMap.RIGHT1PORT);
    rightMotor2 = new Jaguar(RobotMap.RIGHT2PORT);
    
    // setting up the motor groups
    rightSide = new SpeedControllerGroup(rightMotor1, rightMotor2);
    leftSide = new SpeedControllerGroup(leftMotor1, leftMotor2);

    // making differential drive
    drive = new DifferentialDrive(rightSide, leftSide);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new DriveSmooth()); // THIS IS HOW WE DRIVE THE ROBOT
  }


  }
