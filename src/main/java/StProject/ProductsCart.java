package StProject;




public class ProductsCart {
    private String name;
    private String description;
    private double price;

    public String getName() {
        return name;
    }

    public ProductsCart setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductsCart setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public ProductsCart setPrice(double price) {
        this.price = price;
        return this;
    }

}
