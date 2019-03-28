package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DiscGrab extends CommandGroup {
    public  DiscGrab() {
    	addSequential(new ElevatorTimeDown(3));
        addSequential(new DriveTimeReverse(2));
    }
}