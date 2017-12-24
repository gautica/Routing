/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Types;

/**
 *
 * @author markus
 */
public class Point {
    private final double X;
    private final double Y;
    
    public Point(double X, double Y) {
        this.X = X;
        this.Y = Y;
    }
    
    public double getX() {
        return X;
    }
    
    public double getY() {
        return Y;
    }
    
}
