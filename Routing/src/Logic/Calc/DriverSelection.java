/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Calc;

import Logic.Types.Client;

/**
 *
 * @author markus
 */
public class DriverSelection {
    public static Client[] clientsToServe = new Client[20];
    public static Client[] Selection(Client[] clients)  {
        int index = (int) (Math.random() * 100);
        clientsToServe[0] = clients[index];
        for (int i = 1; i < clientsToServe.length; i++) {  
            index = (int) (Math.random() * 100);
            while (!isValid(i, index)) {
                index = (int) (Math.random() * 100);
            }
            clientsToServe[i] = clients[index];
        }
        
        
        return clientsToServe;
    }
    
    private static boolean isValid(int i, int index) {
        for (int j = 0; j < i; j++) {
            if (clientsToServe[j].getID().equals(String.valueOf(index))) {
                return false;
            }
        }
        return true;
    }
    
}
