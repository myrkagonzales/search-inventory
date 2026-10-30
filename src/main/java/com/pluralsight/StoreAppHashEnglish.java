package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StoreAppHashEnglish {
    // declare hashmap where the key is the integer (ID) and value is a product object
    public static HashMap<Integer, Product> inventory = new HashMap<>();

    public static void main(String[] args) {

        // load inventory items from inventory.csv into the HashMap
        loadInventory();

        Scanner scanner = new Scanner(System.in);
        String searchAgain = "Yes"; // yes

        // Bonus LOOP: Keep repeating the search as long as the user answers "yes"
        while (searchAgain.equalsIgnoreCase("Yes") || searchAgain.equalsIgnoreCase("y")) {
            System.out.print("\nWhat item number are you interested in? (ID): ");
            int id = scanner.nextInt();
            scanner.nextLine(); // clear the scanner buffer

            // instant lookup using the hashmap get() method with the ID key
            Product matchedProduct = inventory.get(id);

            if (matchedProduct == null) {
                System.out.println("We do not have that product in inventory.");
            } else {
                System.out.printf("We carry %s and the price is $%.2f%n",
                        matchedProduct.getName(), matchedProduct.getPrice());
            }
            // ask if they want to search again
            System.out.print("\nDo you want to search for another product? (yes/no): ");
            searchAgain = scanner.nextLine().trim();
        }
        System.out.println("Thank you for using the inventory system. Goodbye!");
        scanner.close();
    }

    // method to load products from csv into the HashMap
    public static void loadInventory() {
        try (BufferedReader reader = new BufferedReader(new FileReader("inventory.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(Pattern.quote("|"));

                int id = Integer.parseInt(tokens[0].trim());
                String name = tokens[1].trim();
                double price = Double.parseDouble(tokens[2].trim());

                // put the item into the hashmap using id as the key and a new product as the value
                inventory.put(id, new Product(id, name, price));
            }

        } catch (IOException e) {
            System.out.println("Error loading inventory file: " + e.getMessage());
        }
    }
}

