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
        
        Driver driver = new Driver(DriverSelection.Selection(clients));
        /***one opt***/
        optimize.oneOpt(driver);
        
        
        /***two opt***/
        while (!Optimized.isEnde) {
            optimize.twoOpt(driver);
        }
        for (int i = 0; i < driver.clientsToServe.length; i++) {
            System.out.println(driver.clientsToServe[i].getID());
        }
        System.out.println("****************************");
        /***three opt***/
        optimize.threeOpt(driver);
        for (int i = 0; i < driver.clientsToServe.length; i++) {
            System.out.println(driver.clientsToServe[i].getID());
        }
        /***two opt***/
        Optimized.isEnde = false;
        while (!Optimized.isEnde) {
            optimize.twoOpt(driver);
        }
        
        /**
        int[][] ServeTime = new int[5][5];
        for (int i = 0; i < drivers.length; i++) {
            Client[] temp = drivers[i].clientsToServe;
            for (int j = 0; j < drivers.length; j++) {          // for tour
                drivers[i].clientsToServe = drivers[j].clientsToServe;
                ServeTime[i][j] = drivers[i].getServeTime();
            }
            drivers[i].clientsToServe = temp;           // muss zurück
        }
        
        System.out.println("*********service zeit******************");
        for (int i = 0; i < ServeTime.length; i++) {
            for (int j = 0; j < ServeTime[i].length; j++) {
                System.out.print(ServeTime[i][j] + "  ");
            }
            System.out.println();
        }
        // drive time for every tour
        int[] driveTime = new int[drivers.length];
        for (int i = 0; i < drivers.length; i++) {
            driveTime[i] = (int) (drivers[i].getTourDistance() / 50 *60);
        }
        System.out.println("*********fahrzeit******************");
        for (int i = 0; i < driveTime.length; i++) {
            System.out.println(driveTime[i] + "  ");
        }
        // adding drive time and service time
        System.out.println("*********fahrzeit + dervice time******************");
        int[][] STAndDT = new int[drivers.length][drivers.length];
        for (int i = 0; i < STAndDT.length; i++) {
            for (int j = 0; j < STAndDT.length; j++) {
                STAndDT[i][j] = ServeTime[i][j] + driveTime[i];
            }
        }
        for (int i = 0; i < STAndDT.length; i++) {
            for (int j = 0; j < STAndDT[i].length; j++) {
                System.out.print(STAndDT[i][j] + "  ");
            }
            System.out.println();
        }
        */
        
        MainGUI gui = new MainGUI(driver);
        gui.setVisible(true); 
        gui.setSize(1500, 1000);
    }
}
