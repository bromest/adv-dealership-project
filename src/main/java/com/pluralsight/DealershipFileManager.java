package com.pluralsight;

import java.io.*;
import java.math.BigDecimal;

public class DealershipFileManager {
    Dealership dealership;

    public Dealership getDealership() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("inventory.csv"));

            // Read dealership information
            String line = reader.readLine();

            if (line != null) {
                String[] dealershipInfo = line.split("\\|");

                this.dealership = new Dealership(
                        dealershipInfo[0],
                        dealershipInfo[1],
                        dealershipInfo[2]
                );
            }

            // Read vehicle inventory
            while ((line = reader.readLine()) != null) {

                String[] vehicleData = line.split("\\|");

                int vin = Integer.parseInt(vehicleData[0]);
                int year = Integer.parseInt(vehicleData[1]);
                String make = vehicleData[2];
                String model = vehicleData[3];
                String vehicleType = vehicleData[4];
                String color = vehicleData[5];
                int odometer = Integer.parseInt(vehicleData[6]);
                BigDecimal price = new BigDecimal(vehicleData[7]);

                Vehicle vehicle = new Vehicle(
                        vin,
                        year,
                        make,
                        model,
                        vehicleType,
                        color,
                        odometer,
                        price
                );

                dealership.addVehicleToInventory(vehicle);
            }

            reader.close();

            System.out.println(
                    "Vehicles Loaded: "
                            + dealership.getAllVehicles().size());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dealership;
    }
    // Saves dealership inventory to inventory.csv
    public void saveDealership(Dealership dealership) {
        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter("inventory.csv"))) {

            writer.write(
                    dealership.getName() + "|" +
                            dealership.getAddress() + "|" +
                            dealership.getPhone());

            writer.newLine();

            for (Vehicle vehicle : dealership.getAllVehicles()) {

                writer.write(
                        vehicle.getVin() + "|" +
                                vehicle.getYear() + "|" +
                                vehicle.getMake() + "|" +
                                vehicle.getModel() + "|" +
                                vehicle.getVehicleType() + "|" +
                                vehicle.getColor() + "|" +
                                vehicle.getOdometer() + "|" +
                                vehicle.getPrice());

                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}