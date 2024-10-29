package com.pluralsight;

import java.io.*;
import java.util.List;

public class DealershipFileManager {
    //Method to read from file
    public static Dealership getDealership() {
        Dealership dealership;
        try {
            FileReader fileReader = new FileReader("inventory.csv");
            BufferedReader buffReader = new BufferedReader(fileReader);
            String[] dealershipParts = buffReader.readLine().split("\\|");
            dealership = new Dealership(dealershipParts[0], dealershipParts[1], dealershipParts[2]);
            String line;

            while ((line = buffReader.readLine()) != null) {
                String[] vehicleData = line.split("\\|");
                Vehicle vehicle = new Vehicle(Integer.parseInt(vehicleData[0]), Integer.parseInt(vehicleData[1]), vehicleData[2], vehicleData[3], vehicleData[4], vehicleData[5], Integer.parseInt(vehicleData[6]), Double.parseDouble(vehicleData[7]));
                dealership.addVehicle(vehicle);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dealership;
    }

    public void saveDealership(Dealership dealership) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("inventory.csv", true));
            //Read the first line of the CSV which is the dealership info
            bufferedWriter.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone() + "\n");
            for(Vehicle v : dealership.getInventory()) {
                bufferedWriter.write(v.csvFormatter());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        }
    }

