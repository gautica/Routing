/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithm.Opt;

import Logic.Types.Client;
import Logic.Types.Driver;

/**
 *
 * @author markus
 */
public class TwoOpt  {
    private Driver driver;
    public TwoOpt(Driver driver) {
        this.driver = driver;
    }
    
    public void optimize(int crossIdx1, int crossIdx2) {
        Client[] optimizedArray = new Client[driver.clientsToServe.length];
        
        // Get unaffected entries
        for (int i = 0; i < crossIdx1; i++) {
            optimizedArray[i] = driver.clientsToServe[i];
        }
        for (int i = crossIdx2 + 1; i < driver.clientsToServe.length; i++) {
            optimizedArray[i] = driver.clientsToServe[i];
        }

        //int diff = crossIdx2 - crossIdx1;
        for (int i = crossIdx1; i <= crossIdx2; i++) {
            optimizedArray[i] = driver.clientsToServe[crossIdx2 + crossIdx1 - i];
        }
        driver.clientsToServe = optimizedArray;
    }
    
}
