package com.pluralsight;

import java.util.Scanner;
import java.util.ArrayList;

public class Dealership {
    private String name;
    private String address;
    private int phone;

    public Dealership(String name, String address, int phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getPhone() {
        return phone;
    }
}
