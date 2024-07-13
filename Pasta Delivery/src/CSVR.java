//Alunos Breno Rossi Duarte e Francisco Bley Ruthes
// Alcides Calsavara
// 21/09/2023

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;   

public class CSVR {
    // Functions

    // Print in a text file the canceled orders
    private static void generateCanceledOrdersFile(int week, List<Order> canceledOrders) {
        File fileCanceledOrdersWeek = new File("CanceledOrdersWeek" + week + ".txt");
    
    // "try" and "catch" are errors handling
        try {
            if (fileCanceledOrdersWeek.createNewFile()) {
                try (FileWriter writerOrdersWeek = new FileWriter(fileCanceledOrdersWeek)) {
                    for (Order o : canceledOrders) {
                        String orderInfo = o.order_information();
                        writerOrdersWeek.write(orderInfo);
                        writerOrdersWeek.write("\n");
                    }
                    System.out.println("Canceled orders for week " + week + " have been written to " + fileCanceledOrdersWeek);
                }
            } else {
                System.err.println("Failed to create canceled orders file for week " + week);
            }
        } catch (IOException e) {
            System.err.println("Error creating or writing canceled orders file: " + e.getMessage());
        }
    }

    // Print in a text file the orders in production
    private static void generateProductionFile(int week, Map<String, List<Order>> supermarketOrders, Map<String, List<Order>> restaurantOrders) {
        try {
            System.out.println("-------In Production Week " + week + "-------");
            File file = new File("ProductionWeek" + week + ".txt");
    
            file.createNewFile();
    
            FileWriter writer = new FileWriter(file);
    
            for (String customerName : supermarketOrders.keySet()) {
                List<Order> customerOrderList = supermarketOrders.get(customerName);
                if (customerOrderList != null) {
                    for (Order order : customerOrderList) {
                        String orderInfo = order.order_information();
                        writer.write(orderInfo);
                        writer.write("\n"); 
                    }
                }
            }
    
            for (String customerName : restaurantOrders.keySet()) {
                List<Order> customerOrderList = restaurantOrders.get(customerName);
                if (customerOrderList != null) {
                    for (Order order : customerOrderList) {
                        String orderInfo = order.order_information();
                        writer.write(orderInfo);
                        writer.write("\n");
                    }
                }
            }
    
            writer.close();
            System.out.println("Production file for week " + week + " has been created successfully.");
        } catch (IOException e) {
            System.err.println("Error creating Production file for week " + week + ": " + e.getMessage());
        }
    }

    // Print in a text file the deliveries
    private static void generateDeliveryFile(int week, List<Delivery> deliveries) {
        try {
            System.out.println("-------Delivery Week " + week + "-------");
            File file = new File("DeliveryWeek" + week + ".txt");
    
            file.createNewFile();
    
            FileWriter writer = new FileWriter(file);
    
            for (Delivery delivery : deliveries) {
                String deliveryInfo = delivery.delivery_information();
                
                writer.write(deliveryInfo);
                writer.write("\n");
            }
    
            writer.close();
            System.out.println("Delivery file for week " + week + " has been created successfully.");
        } catch (IOException e) {
            System.err.println("Error creating Delivery file for week " + week + ": " + e.getMessage());
        }
    }

    // Non used function, but it can help create solutions with orders status
    public static Map<String, List<Order>> filterOrdersByStatus(Map<String, List<Order>> supermarketOrders, Map<String, List<Order>> restaurantOrders, String status) {
        Map<String, List<Order>> filteredOrders = new HashMap<>();
    
        for (Map.Entry<String, List<Order>> entry : supermarketOrders.entrySet()) {
            String customerName = entry.getKey();
            List<Order> orders = entry.getValue();
    
            List<Order> filteredList = new ArrayList<>();
    
            for (Order order : orders) {
                if (order.getStatus().equals(status)) {
                    filteredList.add(order);
                }
            }
    
            if (!filteredList.isEmpty()) {
                filteredOrders.put("Supermarket: " + customerName, filteredList);
            }
        }
    
        for (Map.Entry<String, List<Order>> entry : restaurantOrders.entrySet()) {
            String customerName = entry.getKey();
            List<Order> orders = entry.getValue();
    
            List<Order> filteredList = new ArrayList<>();
    
            for (Order order : orders) {
                if (order.getStatus().equals(status)) {
                    filteredList.add(order);
                }
            }
    
            if (!filteredList.isEmpty()) {
                filteredOrders.put("Restaurant: " + customerName, filteredList);
            }
        }
    
        return filteredOrders;
    }

    // Main program
    public static void main(String[] args) {

        // Created objects

        Supermarket super1 = new Supermarket("Gasparin", "2222.2222", "Boa Vista");
        Supermarket super2 = new Supermarket("Big", "3333.4444", "Bacacheri");
        Supermarket super3 = new Supermarket("Carrefour", "5555.7777", "Juvevê");

        Restaurant rest1 = new Restaurant("Dona Ambrosina", "9999.0000", "Bigorrilho");
        Restaurant rest2 = new Restaurant("Cão Veio", "8888.0000", "Batel");
        
        Spaguetti spaguetti = new Spaguetti("Spaguetti", 9, 2000);
        Canelone canelone = new Canelone("Canelone", 12, 1600);
        Talharim talharim = new Talharim("Talharim", 14, 1000);

        // Supermarket array list
        List<Supermarket> existingSupermarkets = new ArrayList<>();
        existingSupermarkets.add(super1);
        existingSupermarkets.add(super2);
        existingSupermarkets.add(super3);

        // Restaurants array list
        List<Restaurant> existingRestaurants = new ArrayList<>();
        existingRestaurants.add(rest1);
        existingRestaurants.add(rest2);

        // Hash maps are similar to a dictionary. It has a key and a matching value
        Map<String, Product> productMap = new HashMap<>();
        productMap.put("Spaguetti", spaguetti);
        productMap.put("Canelone", canelone);
        productMap.put("Talharim", talharim);

        Map<String, Customer> customerMap = new HashMap<>();
        customerMap.put("Gasparin", super1);
        customerMap.put("Big", super2);
        customerMap.put("Carrefour", super3);
        customerMap.put("Dona Ambrosina", rest1);
        customerMap.put("Cão Veio", rest2);

        // Hash maps to save previous values
        Map<String, List<Order>> previousSupermarketOrders = new HashMap<>();
        Map<String, List<Order>> previousRestaurantOrders = new HashMap<>();
        Map<String, List<Order>> previousPreviousSupermarketOrders = new HashMap<>();
        Map<String, List<Order>> previousPreviousRestaurantOrders = new HashMap<>();

        // Weeks loop
        for(int week = 1; week <= 6; week++){

            Map<String, List<Order>> supermarketOrders = new HashMap<>();
            Map<String, List<Order>> restaurantOrders = new HashMap<>();

            System.out.println("WeeK " + week);
            ArrayList<Order> canceled = new ArrayList<Order>();
            ArrayList<Delivery> deliveries = new ArrayList<Delivery>();

            // The first four weeks are for reading "csv" documents
            if ((week == 1) || (week == 2) || (week == 3) || (week == 4)){
                
                // CSV directory
                String csvFile = "C:\\Users\\breno\\Desktop\\Tarefa 1 POO\\week"+ week + ".csv";

                BufferedReader CSVcontent = null;

                String line = "";

                String spacer = ";";

            // Now we are going to create orders objects, separating the canceled ones
            try {
                CSVcontent = new BufferedReader(new FileReader(csvFile));
                while ((line = CSVcontent.readLine()) != null) { 
                    String[] info = line.split(spacer); 
                    int id = Integer.parseInt(info[0]);
                    String customerName = info[1];
                    String typeProductName = info[2];
                    double amount = Double.parseDouble(info[3]);

                    Customer customer = customerMap.get(customerName);
                    Product type_of_pasta = productMap.get(typeProductName);

                    if (amount > type_of_pasta.max_production()){
                        Order orderCanceled = new Order(id, customer, type_of_pasta, amount);
                        //orderCanceled.setStatus("Order Placed, but canceled.");
                        canceled.add(orderCanceled);
                    } else {
                        Order ordersForProduction = new Order(id, customer, type_of_pasta, amount);
                        if (customer instanceof Supermarket) {
                        List<Order> supermarketOrderList = supermarketOrders.get(customerName); 
                        if (supermarketOrderList == null) {
                            supermarketOrderList = new ArrayList<Order>();
                            supermarketOrders.put(customerName, supermarketOrderList); // Add to supermarketOrders Hash map
                        }
                        supermarketOrderList.add(ordersForProduction);
                    } else if (customer instanceof Restaurant) {
                        List<Order> restaurantOrderList = restaurantOrders.get(customerName);
                        if (restaurantOrderList == null) {
                            restaurantOrderList = new ArrayList<Order>();
                            restaurantOrders.put(customerName, restaurantOrderList); // Add to restaurantOrders Hash map --> "(key, value)"
                        }
                        restaurantOrderList.add(ordersForProduction);
                    }
                    }
                    }
                
                // Print in a text file the canceled orders
                generateCanceledOrdersFile(week, canceled); 

            } catch (FileNotFoundException e) {
                System.out.println("File not found: \n" + e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Index out of bound: \n" + e.getMessage());
            } catch (IOException e) {
                System.out.println("IO Error: \n" + e.getMessage());
            } finally {
                if (CSVcontent != null) {
                    try {
                        CSVcontent.close();
                    } catch (IOException e) {
                        System.out.println("IO Error: \n" + e.getMessage());
                    }
                }
            }   
            }
                // Print in a text file the orders in production
                if ((week == 2) || (week == 3) || (week == 4) || (week == 5)){
                    generateProductionFile(week, previousSupermarketOrders, previousRestaurantOrders);
                }

                //This part of the code create all de deliveries for each company
                for (Supermarket supermarket : existingSupermarkets) {
                    List<Order> supermarketOrderList = previousPreviousSupermarketOrders.get(supermarket.getName());
                    if (supermarketOrderList == null) {
                        supermarketOrderList = new ArrayList<Order>();
                        previousPreviousSupermarketOrders.put(supermarket.getName(), supermarketOrderList);
                    }
                    Delivery delivery = new Delivery(supermarket, supermarketOrderList);
                    deliveries.add(delivery);
                }
                
                for (Restaurant restaurant : existingRestaurants) {
                    List<Order> restaurantOrderList = previousPreviousRestaurantOrders.get(restaurant.getName());
                    if (restaurantOrderList == null) {
                        restaurantOrderList = new ArrayList<Order>();
                        previousPreviousRestaurantOrders.put(restaurant.getName(), restaurantOrderList);
                    }
                    Delivery delivery = new Delivery(restaurant, restaurantOrderList);
                    deliveries.add(delivery);
                }

                // Print in a text file the deliveries
                if ((week == 3) || (week == 4) || (week == 5) || (week == 6)){
                    generateDeliveryFile(week, deliveries);
                }

            // Save all the previous values from supermarketOrders and restaurantOrders Hash maps
            previousPreviousRestaurantOrders.putAll(previousRestaurantOrders);
            previousPreviousSupermarketOrders.putAll(previousSupermarketOrders); // 2 weeks ago
            previousSupermarketOrders.putAll(supermarketOrders); 
            previousRestaurantOrders.putAll(restaurantOrders); // 1 week ago
    }
    }
}
