/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Types;

import Logic.Types.Point;

/**
 *
 * @author markus
 */
public class Line {
    private final Point start;
    private final Point end;
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    
    public Point getStart() {
        return this.start;
    }
    
    public Point getEnd() {
        return this.end;
    }
}
