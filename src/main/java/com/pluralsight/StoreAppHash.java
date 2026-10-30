package com.pluralsight;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StoreAppHash {



        // declare hashmap where the key is the integer (ID) and value is a product object
        public static HashMap<Integer, Product> inventory = new HashMap<>();

    public static void main(String[] args) {

        // load inventory items from inventory.csv into the HashMap
        loadInventory();

        Scanner scanner = new Scanner(System.in);
        String searchAgain = "Si"; // yes

        // Bonus LOOP: Keep repeating the search as long as the user answers "si"
        while (searchAgain.equalsIgnoreCase("Si") || searchAgain.equalsIgnoreCase("s")) {
            System.out.print("\n¿Qué número de articulo te interesa? (ID): ");
            int id = scanner.nextInt();
            scanner.nextLine(); // clear the scanner buffer

            // instant lookup using the hashmap get() method with the ID key
            Product matchedProduct = inventory.get(id);

            if (matchedProduct == null) {
                System.out.println("No tenemos ese producto en el inventario. ");
            } else {
                System.out.printf("¡Llevamos %s y el precio es $%.2f%n",
                        matchedProduct.getNombre(), matchedProduct.getPrecio());
            }
            // ask if they want to search again
            System.out.print("\n¿Deseas buscar otro producto? (si/no): ");
            searchAgain = scanner.nextLine().trim();
        }
        System.out.println("!Gracias por usar el sistema de inventario. Adiós!");
        scanner.close();
    }

        //  method to load products from csv into the HashMap
    public static void loadInventory() {
        try (BufferedReader reader = new BufferedReader(new FileReader("inventory.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(Pattern.quote("|"));

                int id = Integer.parseInt(tokens[0].trim());
                String nombre = tokens[1].trim();
                double precio = Double.parseDouble(tokens[2].trim());

                // put the item into the hashmap using aid as the key and a new product as the value
                inventory.put(id, new Product(id, nombre, precio));
            }

        } catch (IOException e) {
            System.out.println("Error al cargar el archivo de inventario: " + e.getMessage());
        }
    }
}
