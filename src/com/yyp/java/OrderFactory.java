package com.yyp.java;

import java.util.*;

public class OrderFactory {

    private int orderID= 0;
    private HashMap<Integer,Order> orderHashMap = new HashMap<>();

    public OrderFactory() {}

    public HashMap<Integer, Order> getOrderHashMap() {
        return orderHashMap;
    }

    public int getTotalOrderQuantity(){
        return orderHashMap.size();
    }

    public int getTotalOrderPrice(){
        int orderPrice = 0;
        for(Map.Entry<Integer, Order> entry : orderHashMap.entrySet()){
            orderPrice = orderPrice + entry.getValue().getOrderPriceAndTips();
        }
        return orderPrice;
    }

    public int getTotalOrderCost(){
        int orderCost = 0;
        for(Map.Entry<Integer, Order> entry : orderHashMap.entrySet()){
            orderCost = orderCost + entry.getValue().getCost();
        }
        return orderCost;
    }

    public int getTotalOrderTips(){
        int orderTips = 0;
        for(Map.Entry<Integer, Order> entry : orderHashMap.entrySet()){
            orderTips = orderTips + entry.getValue().getTips();
        }
        return orderTips;
    }

    public int getTotalOrderProfit(){
        return getTotalOrderPrice()-getTotalOrderCost();
    }

    //no tips when submitting the cart
    public void createOrderID(int orderID, int price, int quantity, int cost, HashMap cartHashMap) {
        Order order = new Order(orderID, price, quantity, cost, cartHashMap);
        orderHashMap.put(orderID,order);
        System.out.println(order.toString());
        orderID++;
    }
    //add tips to the cartHashMap
    public int updateOrderTips(int orderID, int tips) {
        orderHashMap.get(orderID).setTips(tips);
        return orderID;
    }

    public String createOrderID() {
        orderID++;
        return orderID + "";
    }

}
