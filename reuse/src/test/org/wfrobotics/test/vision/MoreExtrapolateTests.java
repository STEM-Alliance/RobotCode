package org.wfrobotics.test.vision;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MoreExtrapolateTests
{
    public  List<Point> points = new ArrayList<Point>();

    @Test
    public void Test1()
    {
        addPoints();
        assertEquals((points.get(0).extrapolate(points.get(1), 2.5)).x_error, 0.075, 0.001);
        assertEquals((points.get(0).extrapolate(points.get(1), 3)).x_error, 0, 0.001);


    }

    public void addPoints()
    {
        points.add(0, new Point(2.43, 0.150,0));
        points.add(1, new Point(1, 0.300,0));
    }
}
//public class Point
//{
//    double time;
//    double x_error;
//    double y_error;
//    VisionTargetInfo target;
//
//
//
//    public Point extrapolate(Point other, double lastTime) {
//        Point ex_pt = new Point(lastTime, ((lastTime - time) * (x_error - other.x_error) + x_error),
//                                        ((lastTime - time) * (y_error - other.y_error) + y_error));
//        return ex_pt;
//    }
//}