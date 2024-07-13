// Inheritance
public class Supermarket extends Customer{

    // Constructor method
    public Supermarket (String name, String CNPJ, String address){
        super(name,CNPJ,address);
    }

    // Type of Customer
    public void customer_type(){
        System.out.println("Supermarket");
    }
}
