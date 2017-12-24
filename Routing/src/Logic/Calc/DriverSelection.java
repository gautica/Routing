/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Calc;

import Logic.Types.Client;
import Logic.Types.Driver;

/**
 *
 * @author markus
 */
public class DriverSelection {
    
    public static void Selection(Driver[] drivers, Client[] clients, String[][] matrix)  {
        
        //sorting clients accroding to angle
        for (int i = 0; i < clients.length - 1; i++) {
            for (int j = i+1; j < clients.length; j++) {
                if (clients[i].getAngle() > clients[j].getAngle()) {
                    Client temp = clients[i];
                    clients[i] = clients[j];
                    clients[j] = temp;
                }
            }
        }
        int count = 0;
        driver: for (int i = 0; i < drivers.length; i++) {    
            // serve time table for every driver
            int[] serveTime = new int[clients.length];
            for (int k = 0; k < serveTime.length; k++) {
                serveTime[k] = (int) Double.parseDouble(matrix[k][i]);
            }
            
            int size = (clients.length - count) < Driver.Capacity? (clients.length - count) : Driver.Capacity;
            Client[] clientPerDriver = new Client[size];
            
            client: for (int j = 0; j < clientPerDriver.length; j++) {
                for (int k = count; k < clients.length; k++) {
                    if (i == (k / Driver.Capacity)) {
                        clientPerDriver[j] = clients[k];
                        count++;
                        continue client;
                    }
                }
            }
            drivers[i] = new Driver(clientPerDriver, serveTime);
            //System.out.println(clientPerDriver.length);
        }
    }
    
}
