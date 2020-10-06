package com.yyp.java;

import javax.swing.*;
import java.util.ArrayList;

public class DishFactory {

    private int nextId = 0;
    private ArrayList<Dish> dishList = new ArrayList<>();

    public DishFactory() {}

    public Dish createDish(String name, String price, String cost, ImageIcon icon) {
            Dish dish = new Dish(nextId++ +"", name, price, cost, icon);
            dishList.add(dish);
            return dish;
    }

    public Dish editDish(String id,String name, String price, String cost, ImageIcon icon) {
        Dish dish = new Dish(id, name, price, cost, icon);
        for(int i = 0; i < dishList.size(); i++) {
            Dish oldDish = dishList.get(i);
            if (oldDish.getID().equals(id)) {
                oldDish.setCost(cost);
                oldDish.setName(name);
                oldDish.setPrice(price);
                oldDish.setIcon(icon);
            }
        }
        dishList.add(dish);
        return dish;
    }

    public int nextId() {
        return nextId;
    }
}
