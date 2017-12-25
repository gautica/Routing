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
    public final int[] familarity = new int[100];
    public Driver(Client[] clientsToServe) {
        this.clientsToServe = clientsToServe;
        tour = new Line[clientsToServe.length + 1];
        for (int i = 0; i < familarity.length; i++) {       // init familarity matrix, 0 stand for strange
            familarity[i] = 0;
        }
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
    
    public void updateFamilarity() {
        for (Client client : clientsToServe) {
            int index = Integer.parseInt(client.getID());
            familarity[index] = 1;
        }
    }
    
    public int getFamilarity() {
        int count = 0;
        for (int f : familarity) {
            if (f == 1) {
                count++;
            }
        }
        return count;
    }
    
    public int getServeTime() {
        int serTime = 0;
        loop1: for (int i = 0; i < familarity.length; i++) {
            for (Client client : clientsToServe) {
                if (i == (Integer.parseInt(client.getID()))) {
                    if (familarity[i] == 0) {
                        serTime += 20;
                    } else {
                        serTime += 10;
                    }
                    continue loop1;
                }
            }
        }
        return serTime;
    }
}
