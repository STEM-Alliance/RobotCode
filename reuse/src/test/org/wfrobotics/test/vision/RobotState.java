package org.wfrobotics.test.vision;

import java.util.ArrayList;
import java.util.List;

import org.wfrobotics.reuse.RobotStateBase;
import org.wfrobotics.reuse.subsystems.vision.CoprocessorData;
import org.wfrobotics.reuse.subsystems.vision.CoprocessorData.VisionTargetInfo;

public class RobotState extends RobotStateBase
{
    public void TODO()
    {
        // --- RIO ---
        // DONE Socket connect/disconnect pretty robust now
        // DONE RIO code up to RobotState pretty cleaned up now
        // TODO Need to integrate cleaned up classes with actual robot code.

        // Interpolation & Timestamps
        // Now thinking we want to compute latency rather than try syncing our time through NTP
        // DONE Copy 254 vision to here, already ported to bottom of real robot's robot state
        // DONE Try feeding our info into that code, can we get an interpolated value out that makes sense?
        // DONE Started to do timestamping (capture time now recorded). Adjust for capture to use latency.

        // Integration with Robot
        // TODO Understand Goal Tracker and Goal Track output, what are the units, when does it change, etc
        // TODO Create vision Command using getAimingParameters() somewhat like 254's 2017 Drive subsystem

        // --- Kangeroo ---
        // DONE Maybe can do timestamping better for latency computation. Low priority.
        // DONE May want to go down to two processes? Low priority.
        // DRL I think the coprocessor is good enough for now
    }

    private static final IRobotState instance = new RobotState();

    public CoprocessorData update;
    public  List<Point> points = new ArrayList<Point>();

    public static RobotState getInstance()
    {
        return instance;
    }

    public void addVisionUpdate(Double time, CoprocessorData coprocessorData)
    {
        update = coprocessorData;

        if (coprocessorData.targets.size() > 0)
        {
            visionInView = true;

            VisionTargetInfo largestTarget = update.targets.get(0);
            for (VisionTargetInfo target : update.targets)
            {
                if ( target.area() > largestTarget.area() || largestTarget == null)
                {
                    largestTarget = target;
                }
            }
            points.add(0, (new Point(time, largestTarget)));
        }
        else {
            visionInView = false;
        }
        if (points.size() > 3)
        {
            printFakeState();
        }
    }
    public void printFakeState()
    {
        System.out.println(getVisionError());
    }
    public double getVisionError()
    {
        return points.get(0).extrapolate(points.get(1), System.currentTimeMillis()).x_error;
    }

    protected void resetRobotSpecificState()
    {
    }

    public void addVisionUpdate(CoprocessorData latest)
    {
    }
}