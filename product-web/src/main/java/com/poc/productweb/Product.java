package com.poc.productweb;

import java.math.BigDecimal;

public class Product {

    private int productId;
    private String name;
    private String description;
    private BigDecimal premium;

    public Product(int productId, String name, String description, BigDecimal premium) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.premium = premium;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPremium() {
        return premium;
    }
}
