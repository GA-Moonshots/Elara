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
import frc.robot.commands.DriveTank;
import frc.robot.commands.ArmHoldKill;
import frc.robot.commands.ArmHoldReset;
import frc.robot.commands.ElevatorDown;
import frc.robot.commands.ElevatorUp;
import frc.robot.commands.KickstandDown;
import frc.robot.commands.KickstandUp;
import frc.robot.commands.DriveSlow;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  // declaring buttons!
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
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    // BUTTONS
    yButton.whileHeld(new ElevatorUp());
    aButton.whileHeld(new ElevatorDown());
    leftBumper.whileHeld(new KickstandDown());
    rightBumper.whileHeld(new KickstandUp());
    leftStickClick.whenPressed(new DriveCommand());
    rightStickClick.whenPressed(new DriveTank());
    startButton.whenPressed(new ArmHoldKill());
    selectButton.whenPressed(new ArmHoldReset());
    xButton.whenPressed(new DriveSlow());
  }

  

}
