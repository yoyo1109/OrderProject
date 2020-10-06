package com.yyp.java;

public class CartEntry {

    private Dish dish;
    private int quantity;

    public CartEntry(Dish dish, int quantity) {
        this.dish = dish;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getDishPrice(){
        return Integer.parseInt(dish.getPrice());
    }

    public int getDishCost(){
        return Integer.parseInt(dish.getCost());
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
