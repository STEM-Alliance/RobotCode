package org.wfrobotics.test.vision;

import org.wfrobotics.reuse.subsystems.vision.CoprocessorData.VisionTargetInfo;

public class Point
{
    double time;
    double x_error;
    double y_error;
    VisionTargetInfo target;


    public Point(double time, VisionTargetInfo target)
    {
        x_error = target.getX();

        this.target = target;
        this.time = time;
    }
    public Point(double time, double x_error, double y_error)
    {
        this.time = time;
        this.x_error = x_error;
        this.y_error = y_error;
    }

    public Point extrapolate(Point other, double lastTime) {
        Point ex_pt = new Point(lastTime, ((lastTime - time) * (x_error - other.x_error) + x_error),
                                        ((lastTime - time) * (y_error - other.y_error) + y_error));
        return ex_pt;
    }
}
