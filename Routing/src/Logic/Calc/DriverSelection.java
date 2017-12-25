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
    public static Client[] clientsToServe = new Client[50];
    public static Client[] Selection(Client[] clients)  {
        
        /**
        clientsToServe[0] = clients[32];
        clientsToServe[1] = clients[66];
        clientsToServe[2] = clients[20];
        clientsToServe[3] = clients[39];
        clientsToServe[4] = clients[29];
        clientsToServe[5] = clients[88];
        clientsToServe[6] = clients[68];
        clientsToServe[7] = clients[95];
        clientsToServe[8] = clients[33];
        clientsToServe[9] = clients[18];
        clientsToServe[10] = clients[4];
        clientsToServe[11] = clients[11];
        clientsToServe[12] = clients[80];
        clientsToServe[13] = clients[97];
        clientsToServe[14] = clients[0];
        clientsToServe[15] = clients[76];
        clientsToServe[16] = clients[2];
        clientsToServe[17] = clients[25];
        clientsToServe[18] = clients[31];
        clientsToServe[19] = clients[10];
        */
        int index = (int) (Math.random() * 100);
        clientsToServe[0] = clients[index];
        for (int i = 1; i < clientsToServe.length; i++) {  
            index = (int) (Math.random() * 100);
            while (!isValid(i, index)) {
                index = (int) (Math.random() * 100);
            }
            clientsToServe[i] = clients[index];
            //System.out.println(index);
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
