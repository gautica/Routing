/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Calc;

import Logic.Types.Point;

/**
 *
 * @author markus
 */
public class Angle {
    /**
     * Calculates a client's angle using the depot as base
     * @param point1
     * @param point2
     * @return X-Angle based on the depot in degrees
     */
    public static double angleTo(Point point1, Point point2) {
        return angleTo(point1.getX(), point1.getY(), point2.getX(), point2.getY());
    };
    
    /**
     * Calculates the x-axis-angle using point 1 as base to point 2 as line-reference point.
     * @param x1 X-Coordinate for point 1
     * @param y1 Y-Coordinate for point 1
     * @param x2 X-Coordinate for point 2
     * @param y2 Y-Coordinate for point 2
     * @return The angle in degrees
     */
    public static double angleTo(double x1, double y1, double x2, double y2) {
        double angle = Math.acos(-(x2 - x1) / Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2))) * 180 / 3.14;
        if ((y2 - y1) < 0)
            return angle;
        if ((y2 - y1) > 0)
            return 360 - angle;
        else if ((x2 - x1) > 0)
            return 180;
        else if ((x2 - x1) < 0)
            return 0;
        else
            System.out.println("die beiden Node sind dieselbe");
        return 0;
    }
    
}
