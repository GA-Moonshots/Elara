package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
public class ArmHoldDown extends Command {
    public ArmHoldDown() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.arm);
      }
    @Override
	protected boolean isFinished() {
		return false;
    }
    public void moveDown(){
      Robot.arm.holdAt-=50;
    }
}