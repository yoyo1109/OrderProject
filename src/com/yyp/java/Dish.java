package com.yyp.java;

import javax.swing.*;

public class Dish {
    private String ID,name,price,cost;
    private ImageIcon icon;

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public Dish(){}

    //create dish property, including ID,name, price, cost
    public Dish(String ID, String name, String price, String cost, ImageIcon icon) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.cost = cost;
        this.icon = icon;
    }

    public String getID() {
        return ID;
    }

    public Dish(String ID,String name, String price) {
        this.ID = ID;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getCost() {
        return cost;
    }

    public ImageIcon getIcon() {
        return icon;
    }
    @Override
    public String toString() {
        return ID + "         " + name + "         " + price + "         ";
    }
}
