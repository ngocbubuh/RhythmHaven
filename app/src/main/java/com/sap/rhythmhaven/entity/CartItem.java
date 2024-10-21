package com.sap.rhythmhaven.entity;

import com.sap.rhythmhaven.entity.ProductEntity;

public class CartItem {
    private ProductEntity product;
    private int quantity;

    public CartItem(ProductEntity product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Getters and setters
    public ProductEntity getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}