package com.pluralsight;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class StoreAppEnglish {

    public static void main(String[] args) {
        // array list to retrieve the inventory list
        ArrayList<Product> inventory = getInventory();

        // sort products alphabetically by name before displaying options
        Collections.sort(inventory, Comparator.comparing(Product::getName));

        // generate scanner
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1 - List all products");
            System.out.println("2 - Look up a product by ID");
            System.out.println("3 - Find products within a price range");
            System.out.println("4 - Add a new product");
            System.out.println("5 - Quit the application");
            System.out.print("Enter an option: ");
            System.out.println("\n-----------------------------------------");

            int command = scanner.nextInt();
            scanner.nextLine(); // consume newline character left behind by nextInt()

            // switch statement
            switch (command) {
                case 1 -> listAllProducts(inventory);
                case 2 -> lookupProductById(inventory, scanner);
                case 3 -> findProductsByPriceRange(inventory, scanner);
                case 4 -> addNewProduct(inventory, scanner);
                case 5 -> {
                    System.out.println("Exiting application. Goodbye!");
                    running = false;
                }
                default ->
                        System.out.println("Invalid option. Please try again.");
            }
        }
        // close scanner
        scanner.close();
    }

    // read inventory.csv line by line into an array list
    private static ArrayList<Product> getInventory() {
        ArrayList<Product> inventory = new ArrayList<>();

        // try with resources automatically closes the bufferedreader when done
        try (BufferedReader bufReader = new BufferedReader(new FileReader("inventory.csv"))) {
            String line;

            // read line by line until reaching end (null)
            while ((line = bufReader.readLine()) != null) {
                // split line by the pipe |
                String[] tokens = line.split(Pattern.quote("|"));

                // parse text values into appropriate types
                int id = Integer.parseInt(tokens[0].trim());
                String name = tokens[1].trim();
                double price = Double.parseDouble(tokens[2].trim());

                // create Product object and store in ArrayList
                inventory.add(new Product(id, name, price));
            }
        } catch (IOException e) {
            System.out.println("Error reading inventory file: " + e.getMessage());
        }
        return inventory;
    }

    // Display all items sorted by name
    private static void listAllProducts(ArrayList<Product> inventory) {
        System.out.println("\n--- ALL PRODUCTS ---");
        for (Product p : inventory) {
            System.out.printf("ID: %d | Name: %-30s | Price: $%.2f%n",
                    p.getId(), p.getName(), p.getPrice());
        }
    }

    // look up specific item by ID
    private static void lookupProductById(ArrayList<Product> inventory, Scanner scanner) {
        System.out.print("Enter product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (Product p : inventory) {
            if (p.getId() == id) {
                System.out.printf("Found: ID %d - %s costs $%.2f%n",
                        p.getId(), p.getName(), p.getPrice());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No product found with that ID.");
        }
    }

    // Filter products between min and max price
    private static void findProductsByPriceRange(ArrayList<Product> inventory, Scanner scanner) {
        System.out.print("Enter minimum price: ");
        double min = scanner.nextDouble();
        System.out.print("Enter maximum price: ");
        double max = scanner.nextDouble();
        scanner.nextLine();

        System.out.printf("%n--- Products between $%.2f and $%.2f ---%n", min, max);
        boolean matched = false;
        for (Product p : inventory) {
            if (p.getPrice() >= min && p.getPrice() <= max) {
                System.out.printf("ID: %d | Name: %-30s | Price: $%.2f%n",
                        p.getId(), p.getName(), p.getPrice());
                matched = true;
            }
        }
        if (!matched) {
            System.out.println("No products found within this price range.");
        }
    }

    // We are now adding a new product at runtime and resorting the list
    private static void addNewProduct(ArrayList<Product> inventory, Scanner scanner) {
        System.out.print("Enter product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        inventory.add(new Product(id, name, price));

        // Keep list sorted alphabetically after adding
        Collections.sort(inventory, Comparator.comparing(Product::getName));
        System.out.println("Product added successfully!");
    }
}

