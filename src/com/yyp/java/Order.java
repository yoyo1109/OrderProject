package com.yyp.java;
import java.util.HashMap;

public class Order {
    private int orderID,
            price,
            quantity,
            cost,tips;

    private HashMap<String, CartEntry> cartHashMap = new HashMap();

    public Order(){}

    public Order(int orderID, int price, int quantity, int cost,int tips) {
        this.orderID = orderID;
        this.price = price;
        this.quantity = quantity;
        this.cost = cost;
        this.tips = tips;
    }

    public Order(int orderID, int price, int quantity, int cost, HashMap<String, CartEntry> cartHashMap) {
        this.orderID = orderID;
        this.price = price;
        this.quantity = quantity;
        this.cost = cost;
        this.cartHashMap = cartHashMap;
    }

    public Order(int orderID, int price, int quantity, int cost,int tips, HashMap cartHashMap) {
        this.orderID = orderID;
        this.price = price;
        this.quantity = quantity;
        this.cost = cost;
        this.tips = tips;
        this.cartHashMap = cartHashMap;
    }


    public int getOrderID() {
        return orderID;
    }

    public int getCost() {
        return cost;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }


    public int getTips() {
        return tips;
    }

    public void setTips(int tips) {
        this.tips = tips;
    }

    public int getOrderPriceAndTips(){
        int orderPriceAndTips = 0;
        orderPriceAndTips = price+ tips;
        return orderPriceAndTips;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", price=" + price +
                ", quantity=" + quantity +
                ", cost=" + cost +
                '}';
    }
}
