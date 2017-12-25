/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Types;

import Logic.Calc.Angle;

/**
 *
 * @author markus
 */
public class Client extends Point{
    private final String ID;
    private final double angle;
    public Client(String ID, int X, int Y) {
        super(X, Y);
        this.ID = ID;
        this.angle = Angle.angleTo(Depot.depot, new Point(X, Y));
    }
    
    public String getID() {
        return this.ID;
    }
    
    public double getAngle() {
        return this.angle;
    }    
}
