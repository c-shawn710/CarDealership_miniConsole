package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class DealershipFileManager {
    //Method to read from file
    public Dealership getDealership() {
        Dealership dealership = new Dealership("D & B Used Cars", "111 Old Benbrook Rd", "817-555-5555");
        try {
            FileReader fileReader = new FileReader("inventory.csv");
            BufferedReader buffReader = new BufferedReader(fileReader);
            String line;

            while ((line = buffReader.readLine()) != null) {
                String[] vehicleData = line.split("\\|");
                if(vehicleData.length == 8) {
                    int vin = Integer.parseInt(vehicleData[0]);
                    int year = Integer.parseInt(vehicleData[1]);
                    String make = vehicleData[2];
                    String model = vehicleData[3];
                    String type = vehicleData[4];
                    String color = vehicleData[5];
                    int mileage = Integer.parseInt(vehicleData[6]);
                    double price = Double.parseDouble(vehicleData[7]);

                    Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
                    dealership.addVehicle(vehicle);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dealership;
    }

    public void saveDealership(Dealership dealership) {
        //Method to write to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("inventory.csv", true))) {
            writer.append()

        } catch (Exception e) {
            System.out.println("Error writing to file");
        }
    }
}
