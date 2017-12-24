/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithm.Opt;

import Logic.Calc.Distance;
import Logic.Types.Client;
import Logic.Types.Depot;
import Logic.Types.Driver;

/**
 *
 * @author yashuai
 */
public class OneOpt {
    private final Driver driver;
    public OneOpt(Driver driver) {
        this.driver = driver;
    }
    /**
     * sorting clients accroding to closest distance
     */
    public void sortClients() {
        int  index = getClosestClientID();
        Client temp = driver.clientsToServe[index];
        driver.clientsToServe[index] = driver.clientsToServe[0];
        driver.clientsToServe[0] = temp;
        for (int i = 1; i < driver.clientsToServe.length; i++) {
            index = getClosestClientID(i);
            temp = driver.clientsToServe[i];
            driver.clientsToServe[i] = driver.clientsToServe[index];
            driver.clientsToServe[index] = temp;
        }
    }
    /**
     * to get closest client between clients and depot
     * @return 
     */
    private int getClosestClientID() {
        double distance = Double.MAX_VALUE;
        int ID = -1;
        for (int i = 0; i < driver.clientsToServe.length; i++) {
            double d = Distance.calcDistance(Depot.depot, driver.clientsToServe[i]);
            if (distance > d) {
                ID = i;
                distance = d;
            }
        }
        return ID;
    }
    /**
     * to get closest client between clients
     * @param startIndex
     * @return 
     */
    private int getClosestClientID(int startIndex) {
        double distance = Double.MAX_VALUE;
        int ID = -1;
        for (int i = startIndex; i < driver.clientsToServe.length; i++) {
            double d = Distance.calcDistance(driver.clientsToServe[i], driver.clientsToServe[startIndex-1]);
            if (distance > d) {
                ID = i;
                distance = d;
            }
        }
        return ID;
    }
}
