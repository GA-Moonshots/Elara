package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DiscRelease extends CommandGroup {
    public  DiscRelease() {
    	addSequential(new ElevatorTimeDown(1));
        addSequential(new DriveTimeReverse(1));
    }
}