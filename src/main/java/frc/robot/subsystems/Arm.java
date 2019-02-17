/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ArmPOV;

/**
 * The system based around the elevator lift motor
 */
public class Arm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  // here's a motor i'm declaring here, sorry Mr. A it's not in a command
  public Jaguar armMotor = new Jaguar(RobotMap.ARMPORT);
  // for the love of God start the elevator down
  public Encoder armEncoder = new Encoder(RobotMap.ARM_ENC_A, RobotMap.ARM_ENC_B, false, Encoder.EncodingType.k4X);
  
  //track the desired position of the arm
  // public int holdAt;


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ArmPOV());
  }


  public Arm(){
  }
}
