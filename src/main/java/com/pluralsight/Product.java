package com.pluralsight;

public class Product {

    // encapsulation; keeping data fields private so outside code cannot change them
    private int id;
    private String name;
    private double price;

    // parameterized constuctor; allowing me to build a product object in one line
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters - method to read the private values
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }





}
