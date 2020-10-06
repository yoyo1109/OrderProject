package com.yyp.java;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class OrderQueue {
    private Queue<Integer> orderQueue = new LinkedList<>();
    private int orderID;

    public ArrayList<Integer> toArrayOrderQueue(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(Integer x : orderQueue){
            arrayList.add(x);
        }
        return arrayList;
    }

    public void addToQueue(Integer orderID){
        orderQueue.offer(orderID);
    }

    public void removeFromQueue(){
        if(orderQueue.size() > 0){
            orderQueue.poll();
        }
    }

    public int size(){
        return orderQueue.size();
    }

    @Override
    public String toString() {
        return "OrderQueue{" +
                "orderID=" + orderID +
                '}';
    }
}
