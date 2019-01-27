package org.wfrobotics.reuse.commands.drive;

import org.wfrobotics.reuse.subsystems.drive.TankSubsystem;
import org.wfrobotics.reuse.IRobotState;
import org.wfrobotics.reuse.config.IButtonConfig;

import edu.wpi.first.wpilibj.command.Command;

/** Turn robot to angle **/
public class TurnToHeading extends Command
{
    protected final IRobotState state = IRobotState.getInstance();
    protected final TankSubsystem drive = TankSubsystem.getInstance();
    protected final IButtonConfig io = IButtonConfig.getInstance();

    double heading;

    public TurnToHeading(double headingFieldRelative)
    {
        requires(drive);
        heading = headingFieldRelative;
    }

    protected void initialize()
    {
        drive.setBrake(true);
        drive.turnToHeading(heading);  // Extra robot iteration of progress
    }

    protected boolean isFinished()
    {
        return drive.onTarget() || Math.abs(io.getThrottle()) > 0.15;
    }
}