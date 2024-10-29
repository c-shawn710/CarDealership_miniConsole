package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership; //Dealership object
    private static Scanner scanner = new Scanner(System.in);

    //Constructor
    public UserInterface() {

    }

    public void display() {
        init(); //Load dealership at the beginning of display()

        while(true) {
            helperMenu();
            int input = scanner.nextInt();
            scanner.nextLine();

            switch(input) {
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetByMileageRequest();
                    break;
                case 6:
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    processGetAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 99:
                    System.out.println("Exiting application...");
                    System.exit(0);
                default:
                    System.out.println("Please select a valid option:");
            }
            System.out.println("\nWould you like to return to the main menu? Y / N");
            String continueInput = scanner.nextLine();
            if(!continueInput.equalsIgnoreCase("y")) {
                System.out.println("Exiting application...");
                System.exit(0);
            }
        }
    }

    private void init(){
        //Create an instance of DealershipFileManager
        DealershipFileManager fileManager = new DealershipFileManager();
        //Call its getDealership() method & assign the dealership that it returns to the UserInterface's this.dealership attribute
        this.dealership = fileManager.getDealership();
    }

    public void processGetByPriceRequest(){
        System.out.println("Enter minimum amount: ");
        double min = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter maximum amount: ");
        double max = scanner.nextDouble();
        scanner.nextLine();
        List<Vehicle> vehicleList = dealership.getVehiclesByPrice(min, max);
        displayVehicles(vehicleList);
    }

    public void processGetByMakeModelRequest() {
        System.out.println("Enter vehicle make: ");
        String make = scanner.nextLine();
        System.out.println("Enter vehicle model: ");
        String model = scanner.nextLine();
        List<Vehicle> vehicleList = dealership.getVehicleByMakeModel(make, model);
        displayVehicles(vehicleList);
    }

    public void processGetByYearRequest() {
        System.out.println("Enter minimum vehicle make year: ");
        int min = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter maximum vehicle make year: ");
        int max = scanner.nextInt();
        scanner.nextLine();
        List<Vehicle> vehicleList = dealership.getVehicleByMakeYear(min, max);
        displayVehicles(vehicleList);
    }

    public void processGetByColorRequest() {
        System.out.println("Enter vehicle color: ");
        String color = scanner.nextLine();
        List<Vehicle> vehicleList = dealership.getVehicleByColor(color);
        displayVehicles(vehicleList);
    }

    public void processGetByMileageRequest() {
        System.out.println("Enter minimum mileage range: ");
        int min = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter maximum mileage range: ");
        int max = scanner.nextInt();
        scanner.nextLine();
        List<Vehicle> vehicleList = dealership.getVehicleByMileage(min, max);
        displayVehicles(vehicleList);
    }

    public void processGetByVehicleTypeRequest() {
        System.out.println("Enter vehicle type: ");
        String type = scanner.nextLine();
        List<Vehicle> vehicleList = dealership.getVehiclesByType(type);
        displayVehicles(vehicleList);
    }

    public void processGetAllVehiclesRequest() {
        System.out.println("VIN|Year|Make|Model|Type|Color|Mileage|Price");
        List<Vehicle> allVehicles = dealership.getAllVehicles();
        displayVehicles(allVehicles);
    }

    public void processAddVehicleRequest() {
        System.out.println("Input the following data\nVehicle Identification Number(VIN): ");
        int addVehicleVIN = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Year:");
        int addVehicleYear = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Make:");
        String addVehicleMake = scanner.nextLine();

        System.out.println("Model:");
        String addVehicleModel = scanner.nextLine();

        System.out.println("Type:");
        String addVehicleType = scanner.nextLine();

        System.out.println("Color:");
        String addVehicleColor = scanner.nextLine();

        System.out.println("Mileage:");
        int addVehicleMileage = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Price: ");
        double addVehiclePrice = scanner.nextDouble();
        scanner.nextLine();

        Vehicle addVehicle = new Vehicle(addVehicleVIN, addVehicleYear, addVehicleMake, addVehicleModel, addVehicleType, addVehicleColor, addVehicleMileage, addVehiclePrice);
        this.dealership.addVehicle(addVehicle);
        DealershipFileManager.saveDealership(this.dealership);


    }

    public void processRemoveVehicleRequest() {
        System.out.println("Enter VIN to remove vehicle: ");
        int vin = scanner.nextInt();
        scanner.nextLine();
        //Remove vehicle with given VIN
        dealership.removeVehicle(vin);
        //Save updated inventory after removal
        DealershipFileManager.saveDealership(this.dealership);
        //Display updated inventory after removal
        displayVehicles(this.dealership.getInventory());
    }

    public void helperMenu() {
        System.out.println("Welcome to the Main Menu!\nPlease select an option:");
        System.out.println("1) Find vehicles within a price range");
        System.out.println("2) Find vehicles by make / model");
        System.out.println("3) Find vehicles by year range");
        System.out.println("4) Find vehicles by color");
        System.out.println("5) Find vehicles by mileage range");
        System.out.println("6) Find vehicles by type (car, truck, SUV, van)");
        System.out.println("7) List ALL vehicles");
        System.out.println("8) Add a vehicle");
        System.out.println("9) Remove a vehicle");
        System.out.println("99) Quit");
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if(vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            for(Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }
        }
    }
}
