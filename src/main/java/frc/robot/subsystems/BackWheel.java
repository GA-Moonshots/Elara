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
import frc.robot.commands.BackWheelUp;

/**
 * The system based around the elevator lift motor
 */
public class BackWheel extends Subsystem {

  public Jaguar backMotor = new Jaguar(RobotMap.BACKPORT);


  @Override
  public void initDefaultCommand() {
  }


  public BackWheel(){
  }
}
