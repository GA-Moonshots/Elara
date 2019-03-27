package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DiscGrab extends CommandGroup {
    public  DiscGrab() {
    	addSequential(new ElevatorTimeUp(1));
        addSequential(new DriveTimeReverse(1));
    }
}