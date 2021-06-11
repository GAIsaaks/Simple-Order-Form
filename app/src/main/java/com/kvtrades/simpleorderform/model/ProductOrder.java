package com.kvtrades.simpleorderform.model;

public class ProductOrder {
    private int orderId;
    private String productTitle;
    private double price;
    private int qty;
    private double sum;
    private String orderDate;

    public ProductOrder(int orderId, String productTitle, double price, int qty, double sum, String orderDate) {
        this.orderId = orderId;
        this.productTitle = productTitle;
        this.price = price;
        this.qty = qty;
        this.sum = sum;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
