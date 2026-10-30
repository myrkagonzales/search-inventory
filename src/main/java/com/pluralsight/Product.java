package com.pluralsight;

public class Product {

    // encapsulation; keeping data fields private so outside code cannot change them
    private int id;
    private String nombre;
    private double precio;

    // parameterized constuctor; allowing me to build a product object in one line
    public Product(int id, String name, double price) {
        this.id = id;
        this.nombre = name;
        this.precio = price;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

}
