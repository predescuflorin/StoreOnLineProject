package StProject;

import javax.persistence.Entity;
import javax.persistence.Table;


public class Products {
    private int id;
    private String productName;
    private String productSpec;
    private String productDescription;
    private double productPrice;

    public int getId() {
        return id;
    }

    public Products setId(int id) {
        this.id = id;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public Products setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getProductSpec() {
        return productSpec;
    }

    public Products setProductSpec(String productSpec) {
        this.productSpec = productSpec;
        return this;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public Products setProductDescription(String productDescription) {
        this.productDescription = productDescription;
        return this;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public Products setProductPrice(double productPrice) {
        this.productPrice = productPrice;
        return this;
    }

    @Override
    public String toString() {
        return "Products{" +
               "id=" + id +
               ", productName='" + productName + '\'' +
               ", productSpec='" + productSpec + '\'' +
               ", productDescription='" + productDescription + '\'' +
               ", productPrice=" + productPrice +
               '}';
    }
}
