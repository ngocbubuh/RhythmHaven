package com.sap.rhythmhaven.entity;

import java.util.Date;
import java.util.List;

public class Invoice {
    private String id;
    private Date date;
    private List<CartItem> items;
    private double totalAmount;

    public Invoice(String id, Date date, List<CartItem> items, double totalAmount) {
        this.id = id;
        this.date = date;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}