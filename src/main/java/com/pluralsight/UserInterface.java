package com.pluralsight;

import  java.util.Scanner;
import java.util.List;
import java.math.BigDecimal;

public class UserInterface {

    private Dealership dealership;
    private Scanner scanner = new Scanner(System.in);

    public UserInterface() {

    }
    public void display(){

        init();//loads dealership from file

        boolean running = true;

        while (running) {

            System.out.println("\n=== Dealership Menu ===");
            System.out.println("1 - Find vehicles by price");
            System.out.println("2 - Find vehicles by make/model");
            System.out.println("3 - Find vehicles by year");
            System.out.println("4 - Find vehicles by color");
            System.out.println("5 - Find vehicles by mileage");
            System.out.println("6 - Find vehicles by type");
            System.out.println("7 - List ALL vehicles");
            System.out.println("0 - Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
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

                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
    //loads dealership from file
    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager();
        this.dealership = fileManager.getDealership();
    }
    private void displayVehicles(List<Vehicle> vehicles) {

        if (vehicles.isEmpty()) {
            System.out.println("No matching vehicles found.");
            return;
        }

        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    private void processGetByPriceRequest() {
        System.out.print("Enter minimum price: ");
        BigDecimal min = new BigDecimal(scanner.nextLine());

        System.out.print("Enter maximum price: ");
        BigDecimal max = new BigDecimal(scanner.nextLine());

        List<Vehicle> vehicles = dealership.getVehiclesByPrice(min, max);

        displayVehicles(vehicles);
    }

    private void processGetByMakeModelRequest(){

        System.out.print("Enter make: ");
        String make = scanner.nextLine();

        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        List<Vehicle> vehicles =
                dealership.getVehiclesByMakeModel(make, model);

        displayVehicles(vehicles);
    }
    private void processGetByYearRequest(){
        System.out.print("Minimum year: ");
        int minYear = Integer.parseInt(scanner.nextLine());

        System.out.print("Maximum year: ");
        int maxYear = Integer.parseInt(scanner.nextLine());

        List<Vehicle> vehicles =
                dealership.getVehiclesByYear(minYear, maxYear);

        displayVehicles(vehicles);
    }
    private void processGetByColorRequest(){
        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        List<Vehicle> vehicles =
                dealership.getVehiclesByColor(color);

        displayVehicles(vehicles);
    }
    private void processGetByMileageRequest(){
        System.out.print("Minimum mileage: ");
        int minMileage = Integer.parseInt(scanner.nextLine());

        System.out.print("Maximum mileage: ");
        int maxMileage = Integer.parseInt(scanner.nextLine());

        List<Vehicle> vehicles =
                dealership.getVehiclesByMileage(minMileage, maxMileage);

        displayVehicles(vehicles);
    }

    private void processGetByVehicleTypeRequest(){
        System.out.print("Enter vehicle type: ");
        String vehicleType = scanner.nextLine();

        List<Vehicle> vehicles =
                dealership.getVehiclesByType(vehicleType);

        displayVehicles(vehicles);
    }

    private void processGetAllVehiclesRequest(){
        List<Vehicle> vehicles =
                dealership.getAllVehicles();

        displayVehicles(vehicles);
    }
}
