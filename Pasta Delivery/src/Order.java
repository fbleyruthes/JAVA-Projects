// Our "Order" class
public class Order {
    //Attributes
    private Customer customer;
    private int id_number;
    private Product type_of_pasta;
    private double amount;
    private String status;

    // Constructor method
    public Order(int id_number, Customer customer, Product type_of_pasta, double amount) {
        this.id_number = id_number;
        this.customer = customer;
        this.type_of_pasta = type_of_pasta;
        this.amount = amount;
        this.status = "";
    }

    // Product item value
    public double item_value () {
        double total = amount * type_of_pasta.kilogramPrice();
        return total;
    }

    // Amount of pasta mass
    public double item_amount () {
        double total = amount;
        return total;
    }

    // Order status
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    // Order information
    public String order_information() {
        String type; 
        if (type_of_pasta instanceof Spaguetti){
            type = "Spaguetti";
        } else {
            if (type_of_pasta instanceof Canelone){
                type = "Canelone";
            } else {
                type = "Talharim";
            }
        }
        String order_details = customer.displayInformation() + "Order ID: " + id_number + "\n" + "Pasta type: " + type + "\n" +
        "Amount: " + amount + "kg" + "\n";
        customer.displayInformation();
        //System.out.println(order_details);
        return order_details;
    }

    // Order information for class Delivery
    public String order_informationDelivery(){
        String type; 
        if (type_of_pasta instanceof Spaguetti){
            type = "Spaguetti";
        } else {
            if (type_of_pasta instanceof Canelone){
                type = "Canelone";
            } else {
                type = "Talharim";
            }
        }
        String order_details = "Order ID: " + id_number + "\n" + "Pasta type: " + type + "\n" +
        "Amount: " + amount + "kg" + "\n";
        return order_details;
    }

    // Kg price
    public double kilogram_price() {
        return type_of_pasta.kilogramPrice();
    }
}
