package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class KickstandToggle extends CommandGroup {
    private boolean isDown = false;
    public  KickstandToggle() {
        if (isDown){
            addSequential(new KickstandUp());
            isDown = !isDown;
        }
        else{
            addSequential(new KickstandDown());
            isDown = !isDown;
        }
    }
}