package com.example.hypersonnalsnsapp.util;

public class ThreadUtil{

    public static void run(int time, Object object){

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        thread.run();
    }


}
