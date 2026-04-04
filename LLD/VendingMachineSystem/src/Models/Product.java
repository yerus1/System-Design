package Models;

import Enums.ProductType;

public class Product {
    private int productID;
    private String productName;
    private Double productPrice;
    private ProductType productType;

    public Product(int productID, String productName, Double productPrice, ProductType productType) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductID() {
        return productID;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public ProductType getProductType() {
        return productType;
    }
}
