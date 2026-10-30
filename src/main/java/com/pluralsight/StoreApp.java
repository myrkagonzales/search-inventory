package com.pluralsight;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.ArrayList;


public class StoreApp {

    public static void main(String[] args) {
        // array list to retrieve the inventory list
        ArrayList<Product> inventory = getInventory();

        // sort products alphabetically by name before displaying options
        Collections.sort(inventory, Comparator.comparing(Product::getNombre));

        // generate scanner
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1 - Mostrar todos los productos"); // lIST ALL THE PRODUCTS
            System.out.println("2 - Buscar un producto por ID"); // Lookup a product by its id
            System.out.println("3 - Buscar productos dentro de un rango de precio"); // Find all products within a price range
            System.out.println("4 - Agregar un nuevo producto"); // Add a new product
            System.out.println("5 - Salir de la aplicación"); // Quit the application
            System.out.println("Ingrese una opción: "); // Enter command
            System.out.println("-----------------------------------------");

            int command = scanner.nextInt();
            scanner.nextLine(); // consume newline character left behind by nextInt()

            // switch staement
            switch (command) {
                case 1 -> listarTodosLosProductos(inventory); //listAllProducts
                case 2 -> buscarProductosPorId(inventory, scanner); //lookupProductById
                case 3 -> buscarProductosPorRangoDePrecio(inventory, scanner); //findProductsByPriceRange
                case 4 -> agregarNuevoProducto(inventory, scanner); //addNewProduct
                case 5 -> {
                    System.out.println("Saliendo de la aplicacíon. !Adiós!"); // Exiting application. Goodbye!
                    running = false;
                }
                default ->
                        System.out.println("Opción no válida. Por favor, inténtelo de nuevo."); // Ivalid option. Please try again
            }

        }
        //close scanner
        scanner.close();
    }

       // read inventory.csv line by line into an array list
       private static ArrayList<Product> getInventory() {
           ArrayList<Product> inventory = new ArrayList<>();

           // try with resources automatically closes the bufferedreader when done
           try (BufferedReader bufReader = new BufferedReader(new FileReader("inventory.csv"))) { // inventory.csv
               String line;

               // read line by line until reaching end (null)
               while ((line = bufReader.readLine()) != null) {
                   // split line by the pipe |
                   String[] tokens = line.split(Pattern.quote("|"));

                   // parse text values into appropriate types
                   int id = Integer.parseInt(tokens[0].trim());
                   String nombre = tokens[1].trim();
                   double precio = Double.parseDouble(tokens[2].trim());


                   // create Product object and store in Arraylist
                   inventory.add(new Product(id, nombre, precio));
               }
           } catch (IOException e) {
               System.out.println("Error al leer el archivo de inventario: " + e.getMessage()); // error reading inventory
           }
           return inventory;
       }
           // Display all items sorted by name
           private static void listarTodosLosProductos(ArrayList<Product> inventory) {
               System.out.println("\n--- TODOS LOS PRODUCTOS ---");
               for (Product p : inventory) {
                   System.out.printf("ID: %d | Nombre: %-30s | Precio: $%.2f%n", // ID: %d | Nombre: %-30s | Precio: $%.2f%n
                           p.getId(), p.getNombre(), p.getPrecio());
               }
           }
           // look up specific item by ID
           private static void buscarProductosPorId(ArrayList<Product> inventory, Scanner scanner) {
               System.out.print("Ingrese el ID del producto: ");
               int id = scanner.nextInt();
               scanner.nextLine();

               boolean found = false;
               for (Product p : inventory) {
                   if (p.getId() == id) {
                       System.out.printf("Encontrado: ID %d - %s cuesta $%.2f%n",
                               p.getId(), p.getNombre(), p.getPrecio());
                       found = true;
                       break;
                   }
               }
               if (!found) {
                   System.out.println("No se encontró ningún producto con el ID: ");
               }
           }
           // Filter products between min and max price
           private static void buscarProductosPorRangoDePrecio(ArrayList<Product> inventory, Scanner scanner) {
               System.out.print("Ingrese el precio mínimo: "); // enter the min. price
               double min = scanner.nextDouble();
               System.out.print("Ingrese el precio máximo: "); // enter the max price
               double max = scanner.nextDouble();
               scanner.nextLine();

               System.out.printf("%n--- Productos entre $%.2f y $%.2f ---%n", min, max);
               boolean matched = false;
               for (Product p : inventory) {
                   if (p.getPrecio() >= min && p.getPrecio() <= max) {
                       System.out.printf("ID: %d | Nombre: %-30s | Precio: $%.2f%n",
                               p.getId(), p.getNombre(), p.getPrecio());
                       matched = true;


                   }
               }
               if (!matched) {
                   System.out.println("No hay productos dentro de este rango de precio.");
               }
           }
           // We are now adding a new product at runtime and resorting the list
           private static void agregarNuevoProducto(ArrayList<Product> inventory, Scanner scanner) {
               System.out.print("Ingrese ID del producto: ");
               int id = scanner.nextInt();
               scanner.nextLine();

               System.out.print("Ingrese nombre del producto: ");
               String nombre = scanner.nextLine();

               System.out.print("Ingrese precio del producto: ");
               double precio = scanner.nextDouble();
               scanner.nextLine();

               inventory.add(new Product(id, nombre, precio));

               // Keep list sorted alphabetically after adding
               Collections.sort(inventory, Comparator.comparing(Product::getNombre));
               System.out.println("¡Producto agregado existosamente!");
    }
}



