package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveAdjustRight extends CommandGroup {
    public  DriveAdjustRight() {
    	addSequential(new DriveToAngle(-30));
        addSequential(new DriveTimeReverse(1.5));
        addSequential(new DriveToAngle(30));
    }
}