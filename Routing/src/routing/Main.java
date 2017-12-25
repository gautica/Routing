/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package routing;

import Extern.ReadWriteExcel;
import Algorithm.Sort.Optimized;
import FinalWork.GUI.MainGUI;
import Logic.Calc.DriverSelection;
import Logic.Types.Client;
import Logic.Types.Driver;
import java.io.IOException;

/**
 *
 * @author markus
 */
public final class Main {
    
    private Main() {} // Leerkonstruktor 
    
    public static void main(String[] args) throws IOException {

        Optimized optimize = new Optimized();
        String[][] matrix = ReadWriteExcel.readXLSFileToArray("clientInfo.xls");    // read client info
        Client[] clients = new Client[matrix.length];
        for (int i = 0; i < clients.length; i++) {
            clients[i] = new Client(matrix[i][0], Integer.parseInt(matrix[i][1]), Integer.parseInt(matrix[i][2]));
        }
        //DriverSelection selection = new DriverSelection();
        Driver driver = new Driver(DriverSelection.Selection(clients));
        int[][] f = new int[20][2];
        int[][] cost = new int[20][2];
        
        for (int i = 0; i < 20; i++) {
            driver.clientsToServe = DriverSelection.Selection(clients);
            /***one opt***/
            optimize.oneOpt(driver);
            /***two opt***/
            Optimized.isEnde = false;
            while (!Optimized.isEnde) {
                optimize.twoOpt(driver);
            }
            /***three opt***/
            optimize.threeOpt(driver);
            /***two opt***/
            Optimized.isEnde = false;
            while (!Optimized.isEnde) {
                optimize.twoOpt(driver);
            }
            cost[i][0] = i;
            cost[i][1] = driver.getServeTime();
            driver.updateFamilarity();
            f[i][0] = i;
            f[i][1] = driver.getFamilarity();
            
        }
        
        ReadWriteExcel.writeXLSFile(f, "Familarity.xls");
        ReadWriteExcel.writeXLSFile(cost, "Cost.xls");
        
        //MainGUI gui = new MainGUI(driver);
        //gui.setVisible(true); 
        //gui.setSize(1500, 1000);
    }
}
