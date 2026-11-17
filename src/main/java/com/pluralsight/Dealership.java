package com.pluralsight;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private final ArrayList<Vehicle> inventory;

    //constructor
    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    //getter
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void addVehicleToInventory(Vehicle vehicle) {
        this.inventory.add(vehicle);
    }

    //get vehicles within a price range
    public List<Vehicle> getVehiclesByPrice(BigDecimal min, BigDecimal max) {
        List<Vehicle> result = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            BigDecimal price = vehicle.getPrice();

            if (price.compareTo(min) >= 0 &&
                    price.compareTo(max) <= 0) {
                    result.add(vehicle);
                }
            }
                return result;
        }
        //get vehicles by make and model
        public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
            List<Vehicle> result = new ArrayList<>();

            for (Vehicle vehicle : inventory) {

                if (vehicle.getMake().equalsIgnoreCase(make)
                        && vehicle.getModel().equalsIgnoreCase(model)) {
                    result.add(vehicle);
                }
            }

            return result;
        }
        //get vehicles by year range
        public List<Vehicle> getVehiclesByYear(int min, int max) {
            List<Vehicle> result = new ArrayList<>();

            for (Vehicle vehicle : inventory) {
                if (vehicle.getYear() >= min && vehicle.getYear() <= max) {
                    result.add(vehicle);
                }
            }

            return result;
        }
        //get vehicles by color
        public List<Vehicle> getVehiclesByColor(String color) {
            List<Vehicle> result = new ArrayList<>();

            for (Vehicle vehicle : inventory) {
                if (vehicle.getColor().equalsIgnoreCase(color)) {
                    result.add(vehicle);
                }
            }

            return result;
        }
        //get vehicles by mileage
        public List<Vehicle> getVehiclesByMileage(int min, int max) {
            List<Vehicle> result = new ArrayList<>();

            for (Vehicle vehicle : inventory) {
                if (vehicle.getOdometer() >= min && vehicle.getOdometer() <= max) {
                    result.add(vehicle);
                }
            }

            return result;
        }
        //get vehicles by type
        public List<Vehicle> getVehiclesByType(String vehicleType) {
            List<Vehicle> result = new ArrayList<>();

            for (Vehicle vehicle : inventory) {
                if (vehicle.getVehicleType().equalsIgnoreCase(vehicleType)) {
                    result.add(vehicle);
                }
            }
            return result;
        }
        //remove vehicle from csv file
    public void removeVehicleFromInventory(int vin) {

        inventory.removeIf(vehicle ->
                vehicle.getVin() == vin);
    }

    //return all vehicles
        public List<Vehicle> getAllVehicles() {

            return new ArrayList<>(inventory);
        }
    }

