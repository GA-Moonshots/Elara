package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveAdjustLeft extends CommandGroup {
    public  DriveAdjustLeft() {
    	addSequential(new DriveToAngle(30));
        addSequential(new DriveTimeReverse(1.5));
        addSequential(new DriveToAngle(-30));
    }
}