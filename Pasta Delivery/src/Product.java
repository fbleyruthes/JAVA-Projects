// Our "Product" class
public class Product {
    //Attributes
    private String ProductName;
    private double kilogram_price;
    private double maxProduction;

    // Constructor method
    public Product (String name, double kilogram_price, double maxProduction) {
        this.ProductName = name;
        this.kilogram_price = kilogram_price;
        this.maxProduction = maxProduction;
    }
    
    // Kg price
    protected double kilogramPrice (){
        return kilogram_price;
    }

    // Production limit
    protected double max_production() {
        return maxProduction;
    }

}
