// Inheritance
public class Restaurant extends Customer{

    // Constructor method
    public Restaurant (String name, String CNPJ, String address){
        super(name,CNPJ,address);
    }

    // Type of Customer
    public void customer_type(){
        System.out.println("Restaurant");
    }
}
