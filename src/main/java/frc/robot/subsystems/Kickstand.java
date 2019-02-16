/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import frc.robot.RobotMap;

/**
 * The system based around the elevator lift motor
 */
public class Kickstand extends Subsystem {

  public Jaguar kickstandMotor = new Jaguar(RobotMap.KICKPORT);

  public Potentiometer kickstandPot = new AnalogPotentiometer(new AnalogInput(RobotMap.KICK_POT));


  @Override
  public void initDefaultCommand() {
  }


  public Kickstand(){
  }
}
