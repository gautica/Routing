/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Types;

import Logic.Calc.Distance;

/**
 *
 * @author markus
 */
public class Driver {
    public static int Capacity = 5;
    public Client[] clientsToServe;
    public final Line[] tour;
    public final int[] serveTime;
    public Driver(Client[] clientsToServe, int[] serveTime) {
        this.clientsToServe = clientsToServe;
        tour = new Line[clientsToServe.length + 1];
        this.serveTime = serveTime;
        //createTour();
    }    
    
    public double getTourDistance() {
        this.createTour();
        return Distance.calcDistance(tour);
    }
    
    public void createTour() {
        tour[0] = new Line(Depot.depot, this.clientsToServe[0]);
        for (int i = 0; i < this.clientsToServe.length - 1; i++) {
            tour[i+1] = new Line(clientsToServe[i], clientsToServe[i+1]);
        }
        tour[tour.length-1] = new Line(clientsToServe[clientsToServe.length-1], Depot.depot);
    }
    
    public int getServeTime() {
        int serTime = 0;
        loop1: for (int i = 0; i < this.serveTime.length; i++) {
            for (Client client : clientsToServe) {
                if (i == (Integer.parseInt(client.getID()) - 1)) {
                    serTime += serveTime[i];
                    continue loop1;
                }
            }
        }
        return serTime;
    }
}
