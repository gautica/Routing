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
public class Crossing {

    protected Point vector1;
    protected Point vector2;
    protected Line line1;
    protected Line line2;
    public Crossing(Line line1, Line line2) {
        double x1 = line1.getStart().getX() - line1.getEnd().getX();
        double y1 = line1.getStart().getY() - line1.getEnd().getY();
        double x2 = line2.getStart().getX() - line2.getEnd().getX();
        double y2 = line2.getStart().getY() - line2.getEnd().getY();
        this.line1 = line1;
        this.line2 = line2;
        vector1 = new Point(x1, y1);
        vector2 = new Point(x2, y2);
    }
    /**
     * Returns the point of crossing lines.
     * @return The crossing point if relevant, 
     */
    public Point crossing() {  
        // Check if the crossing point is relevant
        if (isCrossing()) {
            Point crossOver = calcCrossingPoint();
            
            if (isRelevant(crossOver))
                
                return crossOver;
        }
        return null;
    }
    
    protected Point calcCrossingPoint() {
        double x, y;
        /** l1: x = a
         * l2: y = mx +b
         */
        if (vector1.getX() == 0) {
            x = line1.getEnd().getX();
            double m =  vector2.getY()/ vector2.getX();
            y =  (m*x +  line2.getEnd().getY() - m* line2.getEnd().getX());
        } else if (vector2.getX() == 0) {
            x =  line2.getEnd().getX();
            double m =  vector1.getY()/ vector1.getX();
            y = (m*x +  line1.getEnd().getY() - m* line1.getEnd().getX());
        } else {
            double m1 =  vector1.getY() /  vector1.getX();
            double m2 =  vector2.getY() /  vector2.getX();
            double b1 =  line1.getEnd().getY() - m1 *  line1.getEnd().getX();       // muss end punkt sein
            double b2 =  line2.getEnd().getY() - m2 *  line2.getEnd().getX();
            x = ((b1 - b2) / (m2 - m1));
            y = ((b1 * m2 - b2 * m1) / (m2 - m1));
        }
        return new Point(x, y);
    }
    
    public boolean isCrossing() {
        
        if ((vector1.getX() == 0) && (vector2.getX() == 0)) {
            return false;
        } else if ((vector1.getY()*vector2.getX()) == (vector1.getX()*vector2.getY())) {
            return false;
        } 
        return true;
    }
    
    private boolean isRelevant(Point crossOver) {
        if (isOnLine(crossOver, line1) && isOnLine(crossOver, line2)) {
            return true;
        } else if (isOnLine(crossOver, line1) && (isSamePoint(crossOver, line2.getStart()) || isSamePoint(crossOver, line2.getEnd()))) {
            return true;
        } else if (isOnLine(crossOver, line2) && (isSamePoint(crossOver, line1.getStart()) || isSamePoint(crossOver, line1.getEnd()))) {
            return true;
        }
        return false;
    }
    
    private boolean isOnLine(Point crossOver, Line line) {
        return (crossOver.getX() < Math.max(line.getStart().getX(), line.getEnd().getX()) &&
                crossOver.getX() > Math.min(line.getStart().getX(), line.getEnd().getX()));
    }
    
    private boolean isSamePoint(Point point1, Point point2) {
        return (point1.getX() == point2.getX() && point1.getY() == point2.getY());
    }

}
