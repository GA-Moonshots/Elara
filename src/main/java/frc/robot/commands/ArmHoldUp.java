package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
public class ArmHoldUp extends Command {
    public ArmHoldUp() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.arm);
      }
    @Override
	protected boolean isFinished() {
		return false;
    }
    public void moveUp(){
      Robot.arm.holdAt+=50;
    }
}