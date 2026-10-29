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
        ArrayList<Product> inventory = new ArrayList<>();

        // Adding at least 5 sample products
        inventory.add(new Product(1234,"Kindle Book 6th edition", 125.49));
        inventory.add(new Product(4587, "Attack on Titan vol. 25", 11.35));
        inventory.add(new Product(7894, "Dracula Deluxe Edition", 45.87));
        inventory.add(new Product(2458, "Jane Austen Bookmark", 2.45));
        inventory.add(new Product(1256, "Michael Jackson Thriller CD", 16.58));

        return inventory;


    }


}

