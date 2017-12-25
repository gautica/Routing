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
 * @author markus
 */
public class ThreeOpt  {
    private final Driver driver;
    public ThreeOpt(Driver driver) {
        this.driver = driver;
    }
    
    public void optimize(int i, int j, int k) {
        //Depot[] optimized = new Depot[driver.clientsToServe.length];
        /**
         * start--> i-1
         * i ---- j-1
         * j ---- k-1
         * end <-- k
         * We have 8 possibility for the three Edges-- new connections --
         */
        double[] dists = new double[8];
        // i-1 -- i ==> j-1 -- j ==> k-1 -- k
        dists[0] = distSumme(new Paar(i-1, i), new Paar(j-1, j), new Paar(k-1, k));
        // i-1 -- i ==> j-1 -- k-1 ==> j -- k
        dists[1] = distSumme(new Paar(i-1, i), new Paar(j-1, k-1), new Paar(j, k));
        // i-1 -- j-1 ==> i -- j ==> k-1 -- k
        dists[2] = distSumme(new Paar(i-1, j-1), new Paar(i, j), new Paar(k-1, k));
        // i-1 -- j-1 ==> i --k-1 ==> j -- k
        dists[3] = distSumme(new Paar(i-1, j-1), new Paar(i, k-1), new Paar(j, k));
        // i-1 -- j ==> k-1 -- i ==> j-1 -- k
        dists[4] = distSumme(new Paar(i-1, j), new Paar(k-1, i), new Paar(j-1, k));
        // i-1 -- j ==> k-1 -- j-1 ==> i -- k
        dists[5] = distSumme(new Paar(i-1, j), new Paar(k-1, j-1), new Paar(i, k));
        // i-1 -- k-1 ==> j -- i ==> j-1 -- k
        dists[6] = distSumme(new Paar(i-1, k-1), new Paar(j, i), new Paar(j-1, k));
        // i-1 -- k-1 ==> j -- j-1 ==> i -- k
        dists[7] = distSumme(new Paar(i-1, k-1), new Paar(j, j-1), new Paar(i, k));
        //if (indexOfMin(dists) != 0) System.out.println(indexOfMin(dists));
        // choose min. distance
        switch(indexOfMin(dists)) {
            case 0:
                break;
            case 1:
                //swap(j, k-1);
                createTour(i, j, k, i, j-1, k-1, j);
                break;
            case 2:
                //swap(i, j-1);
                createTour(i, j, k, j-1, i, j, k-1);
                break;
            case 3:
                // i <--> j-1
                //swap(i, j-1);
                // j < --> k-1
                //swap(j, k-1);
                createTour(i, j, k, j-1, i, k-1, j);
                break;
            case 4:     
                // i <--> j
                //swap(i, j);                
                // j-1 <--> k-1
                //swap(j-1, k-1);
                createTour(i, j, k, j, k-1, i, j-1);
               break;
            case 5:
                //swap(i, j);
                //swap(j, k-1);
                //swap(j-1, j);
                createTour(i, j, k, j, k-1, j-1, i);
                break;
            case 6:
                //swap(i, k-1);
                //swap(j-1, j);
                //swap(j, k-1);
                createTour(i, j, k, k-1, j, i, j-1);
                break;
            case 7:
                //swap(i, k-1);
                //swap(j-1, j);
                createTour(i, j, k, k-1, j, j-1, i);
                break;
            default:
                // do nothing
                System.err.println("error in three opt");
                break;     
        }
    }
    
    private void createTour(int i, int j, int k, int index1, int index2, int index3, int index4) {
        Client temp1 = driver.clientsToServe[index1];
        Client temp2 = driver.clientsToServe[index2];
        Client temp3 = driver.clientsToServe[index3];
        Client temp4 = driver.clientsToServe[index4];
        if (index1 == index2 && index3 == index4) {
            driver.clientsToServe[i] = temp1;
            driver.clientsToServe[j] = temp3;
        } else if (index1 == index2) {
            if (i == j-1) {
                driver.clientsToServe[i] = temp1;
                driver.clientsToServe[j] = temp3;
                driver.clientsToServe[k-1] = temp4;
            } else if (j == k-1) {
                driver.clientsToServe[i] = temp1;
                driver.clientsToServe[j-1] = temp3;
                driver.clientsToServe[j] = temp4;
            } else {
                System.out.println("unexpected");
            }
        } else if (index3 == index4) {
            if (i == j-1) {
                driver.clientsToServe[i] = temp1;
                driver.clientsToServe[j] = temp2;
                driver.clientsToServe[k-1] = temp3;
            } else if (j == k-1) {
                driver.clientsToServe[i] = temp1;
                driver.clientsToServe[j-1] = temp2;
                driver.clientsToServe[j] = temp3;
            } else {
                System.out.println("unexpected");
            }
        } else {
            driver.clientsToServe[i] = temp1;
            driver.clientsToServe[j-1] = temp2;
            driver.clientsToServe[j] = temp3;
            driver.clientsToServe[k-1] = temp4;
        }
    }
    
    private int indexOfMin(double[] array) {
        double dist = Double.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (dist > array[i]) {
                dist = array[i];
                index = i;
            }
        }
        return index;
    }
    private double distSumme (Paar p1, Paar p2, Paar p3) {
        double dist = 0;
        if (p1.start == -1) {
            dist += Distance.calcDistance(Depot.depot, driver.clientsToServe[p1.end]);
        } else { 
            dist += Distance.calcDistance(driver.clientsToServe[p1.start], driver.clientsToServe[p1.end]);
        }
        dist += Distance.calcDistance(driver.clientsToServe[p2.start], driver.clientsToServe[p2.end]);
        if (p3.end == driver.clientsToServe.length) {
            dist += Distance.calcDistance(driver.clientsToServe[p3.start], Depot.depot);
        } else {
            dist += Distance.calcDistance(driver.clientsToServe[p3.start], driver.clientsToServe[p3.end]);
        }
        return dist;
    }
    private class Paar{
        int start;
        int end;
        public Paar(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
