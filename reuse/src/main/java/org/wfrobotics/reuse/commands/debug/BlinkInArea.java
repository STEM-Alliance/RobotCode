package org.wfrobotics.reuse.commands.debug;

import org.wfrobotics.reuse.math.geometry.Translation2d;
import org.wfrobotics.reuse.IRobotState;

import edu.wpi.first.wpilibj.command.Command;

public class BlinkInArea extends Command
{
    protected final IRobotState state;
    protected final double top, bottom, left, right;

    public BlinkInArea(double top, double bottom, double left, double right)
    {
        state = IRobotState.getInstance();
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    protected void execute()
    {
        final Translation2d position = state.getLatestFieldToVehicle().getValue().getTranslation();
        final boolean vertically = position.y() < top && position.y() > bottom;
        final boolean horizontally = position.x() > left && position.x() < right;
        final boolean inArea = vertically && horizontally;

        if (inArea)
        {
        }
        else
        {
        }
    }

    protected boolean isFinished()
    {
        return false;
    }

    protected void end()
    {
    }
}