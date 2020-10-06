package com.yyp.java;
import javax.swing.*;
import java.util.HashMap;

public class Menu {

    public Menu() {}

    HashMap<String, Dish> dishHashMap = new HashMap<>();

    public boolean addDishToMenu(Dish dish) {
        dishHashMap.put(dish.getID(),dish);
        return true;
    }

    public boolean containsDish(String dishID){
        return dishHashMap.containsKey(dishID);
    }

    //through dishID get Dish, used in add or remove dishes from cart
    public Dish getDish(String dishID){
        assert containsDish(dishID);
        return dishHashMap.get(dishID);
    }

}
