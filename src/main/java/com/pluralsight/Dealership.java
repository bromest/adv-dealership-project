package com.pluralsight;

import java.util.Scanner;
import java.util.ArrayList;

public class Dealership {
    private String name;
    private String address;
    private int phone;
    private ArrayList<Vehicle> inventory;

    //constructor
    public Dealership(String name, String address, int phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }
    //get vehicles within a price range
    //get vehicles by make and model
    //get vehicles by year range
    //get vehicles by color
    //get vehicles by mileage
    //get vehicles by type
    //return all vehicles
}
