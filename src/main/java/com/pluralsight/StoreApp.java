package com.pluralsight;

import java.util.ArrayList;


public class StoreApp {

    public static void main(String[] args) {
        // array list to retrieve the inventory list
        ArrayList<Product> inventory = getInventory();

        System.out.println("We carry the following products:");
        System.out.println("----------------------------------");

        // loop through the list using standard indexing
        for (int i = 0; i < inventory.size(); i++) {
            Product p = inventory.get(i);
            // %d = integer, %s = string, %.2f = floating point number format to 2 decimals
            System.out.printf("ID: %d | Name: %-20s | Price: $.2f%n",
                    p.getId(), p.getName(), p.getPrice());

        }

    }



    private static ArrayList<Product> getInventory() {
    }


}

