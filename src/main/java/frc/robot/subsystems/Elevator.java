/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * The system based around the elevator lift motor
 */
public class Elevator extends Subsystem {

  public Jaguar elevatorMotor = new Jaguar(RobotMap.ELEVATORPORT);

  @Override
  public void initDefaultCommand() {
  }

  public Elevator(){
  }
}
