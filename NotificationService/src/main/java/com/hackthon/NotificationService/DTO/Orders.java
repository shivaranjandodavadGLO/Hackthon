package com.hackthon.NotificationService.DTO;

import java.util.List;

public class Orders {
    private String orderId;
    private String email;
    private double totalAmount;
    private List<String> items;

    // Constructor
    public Orders(String orderId, String email, double totalAmount, List<String> items) {
        this.orderId = orderId;
        this.email = email;
        this.totalAmount = totalAmount;
        this.items = items;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
// Getters and Setters
}
