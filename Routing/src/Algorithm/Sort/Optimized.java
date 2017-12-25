/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithm.Sort;

import Algorithm.Opt.OneOpt;
import Algorithm.Opt.ThreeOpt;
import Algorithm.Opt.TwoOpt;
import Logic.Calc.Crossing;
import Logic.Types.Driver;

/**
 *
 * @author markus
 */
public class Optimized {
    public static boolean isEnde = false;    
    
    public void threeOpt(Driver driver) {
        driver.createTour();        
        for (int i = 0; i < driver.tour.length-2; i++) {
            for (int j = i+1; j < driver.tour.length-1; j++) {
                for (int k = j+1; k < driver.tour.length; k++) {
                    new ThreeOpt(driver).optimize(i, j, k);
                }
            }
        }
    }
        
    public void twoOpt(Driver driver) {
        driver.createTour();
        for (int i = 2; i < driver.tour.length; i++) {        // don't compare connected two lines don't compare
            for (int j = 0; j < i - 1; j++) {
                if ((i == driver.tour.length - 1) && (j == 0))  // first and last lines are connected
                    continue;
                if (new Crossing(driver.tour[i], driver.tour[j]).crossing() != null) {     // The two crossing lines 
                    
                    new TwoOpt(driver).optimize(j, i - 1);     // i-1 and j are the indexes
                    return; // Important!!!
                }
            }
        }        
        isEnde = true;
    }
    
    public void oneOpt(Driver driver) {
        new OneOpt(driver).sortClients();
    }
    
}
