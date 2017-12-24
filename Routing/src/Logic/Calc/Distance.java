/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Calc;

import Logic.Types.Line;
import Logic.Types.Point;

/**
 *
 * @author markus
 */
public class Distance {

    public static double calcDistance(Point point1, Point point2) {
        return calcDistance(point1.getX(), point1.getY(), point2.getX(), point2.getY());
    }
    
    public static double calcDistance(double X1, double Y1, double X2, double Y2) {
        return Math.sqrt((X1-X2)*(X1-X2) + (Y1-Y2)*(Y1-Y2));
    }
    /**
     * 
     * @param points
     * @return total distance of connacted points
     */
    public static double calcDistance(Point[] points) {
        double distance = 0;
        for (int i = 0; i < points.length - 1; i++) {
            distance += calcDistance(points[i], points[i+1]);
        }
        return distance;
    }
    
    public static double calcDistance(Line[] tour) {
        double distance = 0;
        for (Line line : tour) {
            distance += calcDistance(line.getStart(), line.getEnd());
        }
        return distance;
    }
    
}
