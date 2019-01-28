/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DriveCommand;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Drive extends PIDSubsystem {
  
  // TODO: Research MotionProfileStatus
  
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
    super("Drive", 1.0, 0.0, 0.0); // kick on the PIDSubsystem parent init
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


    // config pid loop
    pidTune = 0;
    double outRange = 0.9;
    this.setInputRange(-180, 180);
    this.setOutputRange(-outRange, outRange);
    this.getPIDController().setContinuous(true);
    this.setPercentTolerance(1);
    this.setAbsoluteTolerance(5.0);  // how close is good enough?
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new DriveCommand());
  }

  @Override
  protected double returnPIDInput() {
    return gyro.getAngle();
  }

  @Override
  protected void usePIDOutput(double output) {
    this.pidTune = output;
  }

  }
