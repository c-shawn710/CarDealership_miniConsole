package com.pluralsight;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;
    private DealershipFileManager fileManager;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
        this.fileManager = new DealershipFileManager();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Vehicle> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Vehicle> inventory) {
        this.inventory = inventory;
    }

    //Get vehicles within a price range
    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        List<Vehicle> result = new ArrayList<>();
        for(Vehicle v : inventory) {
            if(v.getPrice() >= min && v.getPrice() <= max) {
                result.add(v);
            }
        }
        return result;
    }

    public List<Vehicle> getVehicleByMakeModel(String make, String model) {
        List<Vehicle> result = new ArrayList<>();
        for(Vehicle v : inventory) {
            if(v.getMake().equalsIgnoreCase(make) && v.getModel().equalsIgnoreCase(model)) {
                result.add(v);
            }
        }
        return result;
    }

    public List<Vehicle> getVehicleByMakeYear(int min, int max) {
        List<Vehicle> result = new ArrayList<>();
        for(Vehicle v : inventory) {
            if(v.getYear() >= min && v.getYear() <= max) {
                result.add(v);
            }
        }
        return result;
    }

    public List<Vehicle> getVehicleByColor(String color) {
        List<Vehicle> result = new ArrayList<>();
        for(Vehicle v : inventory) {
            if(v.getColor().equalsIgnoreCase(color)) {
                result.add(v);
            }
        }
        return result;
    }

    public List<Vehicle> getVehicleByMileage(double min, double max) {
        List<Vehicle> result = new ArrayList<>();
        for(Vehicle v : inventory) {
            if(v.getOdometer() >= min && v.getOdometer() <= max) {
                result.add(v);
            }
        }
        return result;
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        List<Vehicle> result = new ArrayList<>();
        for(Vehicle v : inventory) {
            if(v.getVehicleType().equalsIgnoreCase(vehicleType)) {
                result.add(v);
            }
        }
        return result;
    }

    public List<Vehicle> getAllVehicles() {
        return inventory;
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
        //Save vehicle data to the CSV file
        fileManager.saveDealership(this);
    }

    public void removeVehicle(int vin) {
        Vehicle toBeRemoved = null;
        for(Vehicle v : inventory) {
            if(v.getVin() == vin){
                toBeRemoved = v;
            }
        }
        if(toBeRemoved != null) {
            inventory.remove(toBeRemoved);
        }
    }

    public Vehicle getVehicleVIN(int vin) {
        for(Vehicle v : inventory) {
            if(v.getVin() == vin) {
                return v;
            }
        }
        return null;
    }
}
