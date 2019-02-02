/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * The system based around the elevator lift motor
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  // here's a motor i'm declaring here, sorry Mr. A it's not in a command
  public Jaguar elevatorMotor = new Jaguar(RobotMap.ELEVATORPORT);

  public Encoder sampleEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
  //public DigitalInput sampleInput;
  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }


  public Elevator(){
    sampleEncoder.setDistancePerPulse(0);
    //sampleInput = new DigitalInput(2);
  }
}
