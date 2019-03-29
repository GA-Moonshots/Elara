/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.KillAll;
import frc.robot.commands.BackWheelDown;
import frc.robot.commands.BackWheelUp;
import frc.robot.commands.DiscGrab;
import frc.robot.commands.DiscRelease;
import frc.robot.commands.ElevatorDown;
import frc.robot.commands.ElevatorUp;
import frc.robot.commands.KickstandToggle;
import frc.robot.commands.KickstandUpManual;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public static Joystick xbox = new Joystick(0);
  public static Button xButton = new JoystickButton(xbox, 3);
  public static Button aButton = new JoystickButton(xbox, 1);
  public static Button bButton = new JoystickButton(xbox, 2);
  public static Button yButton = new JoystickButton(xbox, 4);
  public static Button leftBumper = new JoystickButton(xbox, 5);
  public static Button rightBumper = new JoystickButton(xbox, 6);
  public static Button selectButton = new JoystickButton(xbox, 7);
  public static Button startButton = new JoystickButton(xbox, 8);
  public static Button leftStickClick = new JoystickButton(xbox, 9);
  public static Button rightStickClick = new JoystickButton(xbox, 10);
  
  
  public OI(){

    yButton.whileHeld(new ElevatorUp());
    aButton.whileHeld(new ElevatorDown());
    leftBumper.whenPressed(new KickstandToggle());
    rightBumper.whileHeld(new BackWheelDown());
    leftStickClick.whenPressed(new DriveCommand());
    rightStickClick.whenPressed(new KickstandUpManual());
    startButton.whileHeld(new BackWheelUp());
    selectButton.whenPressed(new KillAll());
    xButton.whenPressed(new DiscGrab());
    bButton.whenPressed(new DiscRelease());
  }

  

}
