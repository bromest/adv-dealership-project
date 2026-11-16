package com.pluralsight;

import  java.util.Scanner;
import java.util.List;

public class UserInterface {

    private Dealership dealership;
    private Scanner scanner = new Scanner(System.in);

    public UserInterface() {

    }
    public void display(){

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
        }
    }
}
