package com.xrw.thread;


/**
 * @Created with IDEA
 * @author:Yuwenbo
 * @Date:2018/3/18
 * @Time:14:16
 * @JDK versions: 1.8.0_101
 * @note:卖票
 */
class Ticket implements Runnable{

    private int num=1000;
    private Object obj = new Object();
    @Override
    public void run() {
        synchronized (obj) {
            while(true){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(num>=0){
                    System.out.println(Thread.currentThread().getName()+"---"+num--);
                }
            }
        }
    }
}
public class ThreadDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        Thread thread1 = new Thread(ticket);
        Thread thread2 = new Thread(ticket);
        thread1.start();
        thread2.start();
    }
}
