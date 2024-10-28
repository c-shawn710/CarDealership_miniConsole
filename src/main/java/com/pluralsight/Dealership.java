package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
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
            if(v.getMake().equalsIgnoreCase(make) && v.getMake().equalsIgnoreCase(model)) {
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
    }

    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }
}
