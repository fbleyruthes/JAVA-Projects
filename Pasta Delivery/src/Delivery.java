import java.util.ArrayList;
import java.util.List;

// Our "Delivery" class
public class Delivery {
    //Attributes
    private double delivery_full_price;
    private double delivery_full_mass;
    private Customer customer;
    private ArrayList<Order> deliverys;

    // Constructor method
    public Delivery (Customer customer, List<Order> orders) { 
        this.customer = customer;
        this.deliverys = new ArrayList<Order>();
        this.delivery_full_price = total_value(orders);
        this.delivery_full_mass = total_mass(orders);
    }

    // Delivery value
    public double total_value(List<Order> orders) {
        delivery_full_price = 0;
        for (Order order : orders) {
            delivery_full_price += order.item_value();
        }
        if (customer instanceof Supermarket) {
            delivery_full_price *= 0.9;
        }
        return delivery_full_price;  
    }

    // Delivery mass amount
    public double total_mass(List<Order> orders){
        delivery_full_mass = 0;
        for (Order order : orders) {
            delivery_full_mass += order.item_amount();
        }
        return delivery_full_mass;
    }

    // Delivery information
    public String delivery_information() {
        String type; 
        if (customer instanceof Supermarket){
            type = "Supermarket";
        } else {
            type = "Restaurant";
        }
        
        StringBuilder delivery_details = new StringBuilder();
        delivery_details.append("\nClient Type: ").append(type).append(customer.displayInformation());
        
        for (Order o : deliverys) {
            delivery_details.append(o.order_informationDelivery()).append("\n");
        }
        String delivery_full_massFormatted = String.format("%.2f", delivery_full_mass);
        String delivery_full_priceFormatted = String.format("%.2f", delivery_full_price);
        delivery_details.append("Total mass: ").append(delivery_full_massFormatted).append("kg\n");
        delivery_details.append("Total Price: $").append(delivery_full_priceFormatted).append("\n\n");
    
        return delivery_details.toString();
    }
}