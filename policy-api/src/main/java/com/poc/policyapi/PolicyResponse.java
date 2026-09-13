package com.poc.policyapi;

public class PolicyResponse {

    private String customerName;
    private String customerId;
    private Integer productId;
    private String productName;

    public PolicyResponse(String customerName, String customerId,
                          Integer productId, String productName) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.productId = productId;
        this.productName = productName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }
}
