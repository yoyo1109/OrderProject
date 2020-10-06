package com.yyp.java;

import javax.swing.table.DefaultTableModel;

public class TimerThread extends Thread {

    /**
     * Allocates a new {@code Thread} object. This constructor has the same
     * effect as {@linkplain #Thread(ThreadGroup, Runnable, String) Thread}
     * {@code (null, null, gname)}, where {@code gname} is a newly generated
     * name. Automatically generated names are of the form
     * {@code "Thread-"+}<i>n</i>, where <i>n</i> is an integer.
     */
    private DefaultTableModel tableModel;
    private OrderQueue orderQueue;


    public TimerThread(DefaultTableModel tableModel, OrderQueue orderQueue) {
        this.tableModel = tableModel;
        this.orderQueue = orderQueue;
    }

    public void run(){

        while(true){
            System.out.println("thread: " + orderQueue.size());
            int i = 50;
            while (i>0) {
                System.out.println("sleeping: " + i--);
                updateQueueAndView();
                pause();
            }
            orderQueue.removeFromQueue();
        }
    }

    public void pause(){
        try{
            sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateQueueAndView(){
        tableModel.setRowCount(0);
        for(Integer i : orderQueue.toArrayOrderQueue()){
            tableModel.addRow(new String[]{i +""});
        }
    }
}
