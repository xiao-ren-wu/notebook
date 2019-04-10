package com.xrw.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Created with IDEA
 * @author:Yuwenbo
 * @Date:2018/3/18
 * @Time:23:13
 * @JDK versions: 1.8.0_101
 */
class Resource{
    private boolean flag=false;
    private int count=1;
    private String name;
    private Lock lock=new ReentrantLock();
    private Condition producer =lock.newCondition();
    private Condition consumer =lock.newCondition();
    public void setName(String name){
        lock.lock();
        try {
            while (flag) {
                try {
                    producer.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.name = name;
            System.out.println(Thread.currentThread().getName()+"......生产者......."+ name + count++);
            flag = true;
            consumer.signal();
        } finally {
            lock.unlock();
        }
    }
    public void outName(){
        lock.lock();
        try{
            while(!flag){
                try {
                    consumer.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+".....消费者......"+name+count);
            flag=false;
            producer.signal();
        }finally {
            lock.unlock();
        }
    }
}
class Consumer implements Runnable{
    private Resource res;

    public Consumer(Resource res) {
        this.res = res;
    }

    @Override
    public void run() {
        while(true){
            res.outName();
        }
    }
}
class Producer implements Runnable{
    private Resource res;

    public Producer(Resource res) {
        this.res = res;
    }

    @Override
    public void run() {
        while(true){
            res.setName("烤鸭");
        }
    }
}
public class LockDemo {
    public static void main(String[] args) {
        Resource res = new Resource();
        Thread pro1 = new Thread(new Producer(res));
        Thread pro2 = new Thread(new Producer(res));
        Thread con1 = new Thread(new Consumer(res));
        Thread con2 = new Thread(new Consumer(res));
        pro1.start();
        pro2.start();
        con1.start();
        con2.start();
    }
}
