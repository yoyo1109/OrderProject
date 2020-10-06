package com.yyp.java;
import java.util.*;

public class Cart {

    private HashMap<String, CartEntry> idToCartEntryMap;

    public Cart() {
        this.idToCartEntryMap = new HashMap<>();
    }
    public HashMap<String, CartEntry> getIdToCartEntryMap() {
        return idToCartEntryMap;
    }

    public int getDishQuantity(String dishID){
        return idToCartEntryMap.get(dishID).getQuantity();
    }

    public int getOrderQuantity(){
        int orderQuantity = 0;
        for(Map.Entry<String, CartEntry> entry : idToCartEntryMap.entrySet()){
            orderQuantity = orderQuantity + entry.getValue().getQuantity();
        }
        return orderQuantity;
    }

    public int getOrderPrice(){
        int orderPrice = 0;
        for(Map.Entry<String, CartEntry> entry : idToCartEntryMap.entrySet()){
            orderPrice = orderPrice + entry.getValue().getQuantity() * entry.getValue().getDishPrice();
        }
        return orderPrice;
    }

    public int getOrderCost(){
        int orderCost = 0;
        for(Map.Entry<String, CartEntry> entry : idToCartEntryMap.entrySet()){
            orderCost = orderCost + entry.getValue().getQuantity() * entry.getValue().getDishCost();
        }
        return orderCost;
    }

    public boolean containsDish(String dishID){
        return idToCartEntryMap.containsKey(dishID);
    }

    public boolean addToCart(Dish dish){
        String dishID = dish.getID();
        if(containsDish(dishID)){
            CartEntry cartEntry = idToCartEntryMap.get(dishID);
            cartEntry.setQuantity(cartEntry.getQuantity() + 1);
            idToCartEntryMap.put(dishID, cartEntry);
            return false;
        }else{
            idToCartEntryMap.put(dishID, new CartEntry(dish,1));
            return true;
        }
    }

    public void removeFromCart(Dish dish){
        String dishID = dish.getID();
        if(containsDish(dishID)){
            CartEntry cartEntry = idToCartEntryMap.get(dishID);
           int dishQuantity = cartEntry.getQuantity();
           if(dishQuantity > 1){
               dishQuantity--;
               cartEntry.setQuantity(dishQuantity);
               idToCartEntryMap.put(dishID, cartEntry);
           }else if(dishQuantity == 1){
               idToCartEntryMap.remove(dishID);
           }
        }
    }

    public void clearCart(){
        idToCartEntryMap.clear();
    }
}
