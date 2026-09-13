package com.poc.policyapi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "name")
    private String name;

    public Integer getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }
}
