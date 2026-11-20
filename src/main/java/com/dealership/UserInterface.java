package com.dealership;

import java.time.LocalDate;
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
            System.out.println("8 - Add Vehicle");
            System.out.println("9 - Remove Vehicle");
            System.out.println("10 - Sell / Lease a vehicle");
            System.out.println("99 - Exit");
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
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 10:
                    processSellOrLeaseVehicleRequest();
                    break;
                case 99:
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
    //gets vehicle by price from csv file
    private void processGetByPriceRequest() {
        System.out.print("Enter minimum price: ");
        BigDecimal min = new BigDecimal(scanner.nextLine());

        System.out.print("Enter maximum price: ");
        BigDecimal max = new BigDecimal(scanner.nextLine());

        List<Vehicle> vehicles = dealership.getVehiclesByPrice(min, max);

        displayVehicles(vehicles);
    }
    //gets vehicle by make from csv file
    private void processGetByMakeModelRequest(){

        System.out.print("Enter make: ");
        String make = scanner.nextLine();

        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        List<Vehicle> vehicles =
                dealership.getVehiclesByMakeModel(make, model);

        displayVehicles(vehicles);
    }
    //gets vehicle by year from csv file
    private void processGetByYearRequest(){
        System.out.print("Minimum year: ");
        int minYear = Integer.parseInt(scanner.nextLine());

        System.out.print("Maximum year: ");
        int maxYear = Integer.parseInt(scanner.nextLine());

        List<Vehicle> vehicles =
                dealership.getVehiclesByYear(minYear, maxYear);

        displayVehicles(vehicles);
    }
    //gets vehicle by color from csv file
    private void processGetByColorRequest(){
        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        List<Vehicle> vehicles =
                dealership.getVehiclesByColor(color);

        displayVehicles(vehicles);
    }
    //gets vehicle by mileage from csv file
    private void processGetByMileageRequest(){
        System.out.print("Minimum mileage: ");
        int minMileage = Integer.parseInt(scanner.nextLine());

        System.out.print("Maximum mileage: ");
        int maxMileage = Integer.parseInt(scanner.nextLine());

        List<Vehicle> vehicles =
                dealership.getVehiclesByMileage(minMileage, maxMileage);

        displayVehicles(vehicles);
    }
        //gets vehicle by type from csv file
    private void processGetByVehicleTypeRequest(){
        System.out.print("Enter vehicle type: ");
        String vehicleType = scanner.nextLine();

        List<Vehicle> vehicles =
                dealership.getVehiclesByType(vehicleType);

        displayVehicles(vehicles);
    }
    // add vehicle to the csv file
    private void processAddVehicleRequest() {

        System.out.print("VIN: ");
        int vin = Integer.parseInt(scanner.nextLine());

        System.out.print("Year: ");
        int year = Integer.parseInt(scanner.nextLine());

        System.out.print("Make: ");
        String make = scanner.nextLine();

        System.out.print("Model: ");
        String model = scanner.nextLine();

        System.out.print("Type: ");
        String type = scanner.nextLine();

        System.out.print("Color: ");
        String color = scanner.nextLine();

        System.out.print("Mileage: ");
        int mileage = Integer.parseInt(scanner.nextLine());

        System.out.print("Price: ");
        BigDecimal price =
                new BigDecimal(scanner.nextLine());

        Vehicle vehicle = new Vehicle(
                vin,
                year,
                make,
                model,
                type,
                color,
                mileage,
                price);

        dealership.addVehicleToInventory(vehicle);

        new DealershipFileManager()
                .saveDealership(dealership);

        System.out.println("Vehicle added.");
    }

    private void processRemoveVehicleRequest() {

        System.out.print("Enter VIN to remove: ");

        int vin =
                Integer.parseInt(scanner.nextLine());

        dealership.removeVehicleFromInventory(vin);

        new DealershipFileManager()
                .saveDealership(dealership);

        System.out.println("Vehicle removed.");
    }
    //display all vehicle in the csv file
    private void processGetAllVehiclesRequest(){
        List<Vehicle> vehicles =
                dealership.getAllVehicles();

        displayVehicles(vehicles);
    }
    private void processSellOrLeaseVehicleRequest() {

        System.out.print("Enter VIN of the vehicle to sell or lease: ");
        int vin = Integer.parseInt(scanner.nextLine().trim());

        Vehicle vehicle = null;
        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin() == vin) {
                vehicle = v;
                break;
            }
        }

        System.out.print("Enter contract date (YYYYMMDD): ");
        String date = scanner.nextLine().trim();

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine().trim();

        System.out.print("Enter customer email: ");
        String customerEmail = scanner.nextLine().trim();

        System.out.print("Is this a Sale or a Lease? (S/L): ");
        String type = scanner.nextLine().trim().toUpperCase();

        Contract contract;
        if (type.equals("S")) {
            System.out.print("Would you like to finance this purchase? (Y/N): ");
            boolean isFinanced = scanner.nextLine().trim().equalsIgnoreCase("Y");
            contract = new SalesContract(date, customerName, customerEmail, vehicle, isFinanced);
        } else if (type.equals("L")) {
            int currentYear = LocalDate.now().getYear();
            if ((currentYear - vehicle.getYear()) > 3) {
                System.out.println("Error: Vehicles over 3 years old cannot be leased.");
                return;
            }
            contract = new LeaseContract(date, customerName, customerEmail, vehicle);
        } else {
            System.out.println("Invalid selection. Transaction cancelled.");
            return;
        }
        // Save contract to contracts.csv
        ContractDataManager contractManager = new ContractDataManager();
        contractManager.saveContract(contract);

        // Remove vehicle from inventory and save updated inventory.csv
        dealership.removeVehicleFromInventory(vehicle.getVin());
        new DealershipFileManager().saveDealership(dealership);

        System.out.printf("Transaction complete! Total Price: $%.2f | Monthly Payment: $%.2f%n",
                contract.getTotalPrice(), contract.getMonthlyPayment());
    }

}
