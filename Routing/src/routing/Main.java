/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package routing;

import Extern.ReadWriteExcel;
import Algorithm.Sort.Optimized;
import FinalWork.GUI.MainGUI;
import Logic.Calc.Distance;
import Logic.Calc.DriverSelection;
import Logic.Types.Client;
import Logic.Types.Depot;
import Logic.Types.Driver;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author markus
 */
public final class Main {
    private static final Client[] clients = new Client[24];
    private final static int driverNumber = 24 / 5 + 1;     // Annahme: Kapazität 5
    private Main() {} // Leerkonstruktor 
    
    public static void main(String[] args) throws IOException {

        Optimized optimize = new Optimized();
        
        initTestClients();
        String[][] matrix = ReadWriteExcel.readXLSFileToArray();    // read serve time excel
 
        
        Driver drivers[] = new Driver[driverNumber];
        DriverSelection.Selection(drivers, clients, matrix);
        /***one opt***/
        for (Driver driver : drivers) {
            optimize.oneOpt(driver);
        }
        
        /***two opt***/
        for (Driver driver : drivers) {
            while (!Optimized.isEnde) {
                optimize.twoOpt(driver);
            }
            Optimized.isEnde = false;
        }
        
        /***three opt***/
        for (Driver driver : drivers) {
            optimize.threeOpt(driver);
        }
        
        /** matching**/
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
        
        
        MainGUI gui = new MainGUI(drivers);
        gui.setVisible(true);
        /**
        * zum testen
         */
        
        double totalDist = 0;
        for (int i = 0; i < drivers.length; i++) {
            System.out.println("*********driver" + i + "******************");
            System.out.println("depot");
            for (int j = 0; j < drivers[i].clientsToServe.length; j++) {
                System.out.println(drivers[i].clientsToServe[j].getID());
            }
            //System.out.println(drivers[i].clientsToServe[drivers[i].clientsToServe.length-1].getID());
            System.out.println("depot");
            System.out.println("the total distance of driver " + i + " is: " + drivers[i].getTourDistance());
            System.out.println("the total service time of driver " + i + " is: " + drivers[i].getServeTime());
            totalDist += drivers[i].getTourDistance();
        }
        System.out.println("the total distance of " + drivers.length + " drivers is: " + totalDist);
       
        
        writeDistanceExcel(clients);
        
        
            
    }
    private static void writeMathExcel(Driver[] drivers) {
        
    }
    private static void writeDistanceExcel(Client[] clients) {
        String[][] distMatrix = new String[clients.length+1+1][clients.length+1+1];
        distMatrix[0][0] = "";
        for (int i = 1; i < clients.length+2; i++) {
            distMatrix[0][i] = i - 1 + "";
            distMatrix[i][0] = i - 1 + "";
            
            if (i == 1) {
                distMatrix[1][i] = "0";
            } else {
                distMatrix[1][i] = (int) Distance.calcDistance(Depot.depot, clients[i-2]) + "";
                distMatrix[i][1] = (int) Distance.calcDistance(Depot.depot, clients[i-2]) + "";
            }   
        } 
        for (int i = 0; i < clients.length; i++) {
            for (int j = 0; j < clients.length; j++) {
                distMatrix[i+2][j+2] = (int) Distance.calcDistance(clients[i], clients[j]) + "";
            }  
        }
        
        try {
            ReadWriteExcel.writeXLSFile(distMatrix);
            //ReadWriteExcel.readXLSFile();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static void writeCostExcel(Driver[] drivers) {
        // print matix for fahrer
        String[][] table = new String[6][5];
        table[0][0] = "";
        table[0][1] = "ST";
        table[0][2] = "D";
        table[0][3] = "ST + D";
        table[0][4] = "Kosten";
        
        table[1][0] = "driver1";
        table[2][0] = "driver2";
        table[3][0] = "driver3";
        table[4][0] = "driver4";
        table[5][0] = "driver5";
        
        for (int i = 1; i < table.length; i++) {
            table[i][1] = ((int) (drivers[i-1].clientsToServe.length - 2) * 10) +"";
            table[i][2] = (int) (Distance.calcDistance(drivers[i-1].clientsToServe) / 80 *60) +"";
            table[i][3] = Integer.parseInt(table[i][1]) + Integer.parseInt(table[i][2]) + "";
            table[i][4] = "";
        }
        
        try {
            ReadWriteExcel.writeXLSFile(table);
            ReadWriteExcel.readXLSFile();
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void initTestClients() {
        clients[0] = new Client("1", 522, 222);
        clients[1] = new Client("2", 2222, 2222);
        clients[2] = new Client("3", 2000, 2000);
        clients[3] = new Client("4", 300, 100);
        clients[4] = new Client("5", 222, 235);
        clients[5] = new Client("6", 150, 3000);
        clients[6] = new Client("7", 190, 1619);
        clients[7] = new Client("8", 2062, 1428);
        clients[8] = new Client("9", 1600, 2570);
        clients[9] = new Client("10", 2222, 100);
        clients[10] = new Client("11", 1315, 336);
        clients[11] = new Client("12", 2750, 2434);
        clients[12] = new Client("13", 677, 644);
        clients[13] = new Client("14", 1899, 1622);
        clients[14] = new Client("15", 330, 362);
        clients[15] = new Client("16", 592, 1877);
        clients[16] = new Client("17", 500, 950);
        clients[17] = new Client("18", 745, 1378);
        clients[18] = new Client("19", 1809, 2705);
        clients[19] = new Client("20", 2853, 1747);
        clients[20] = new Client("21", 1829, 111);
        clients[21] = new Client("22", 299, 2899);
        clients[22] = new Client("23", 211, 666);
        clients[23] = new Client("24", 999, 1600);
        
    }
    
}
