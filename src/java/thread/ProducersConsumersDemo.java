package com.xrw.thread;

/**
 * @Created with IDEA
 * @author:Yuwenbo
 * @Date:2018/3/18
 * @Time:20:24
 * @JDK versions: 1.8.0_101
 * @apiNote 生产者消费者问题（多生产者，多消费者）
 */
class Resource {
    private String name;
    private int count = 1;
    private boolean flag = false;

    public synchronized void setName(String name) {
        while (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name = name + count;
        count++;
        System.out.println(Thread.currentThread().getName() + ".....生产者....." + this.name);
        flag = true;
        notifyAll();
    }

    public synchronized void out() {
        while (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + ".....消费者.............." + this.name);
        flag = false;
        notifyAll();
    }
}

class Producer implements Runnable {
    private Resource r;

    public Producer(Resource r) {
        this.r = r;
    }

    @Override
    public void run() {
        while (true) {
            r.setName("烤鸭");
        }
    }
}

class Consumer implements Runnable {
    private Resource r;

    public Consumer(Resource r) {
        this.r = r;
    }

    @Override
    public void run() {
        while(true){
            r.out();
        }
    }
}

/**
 * @author Xiaorenwu
 */
public class ProducersConsumersDemo {
    public static void main(String[] args) {
        Resource res = new Resource();
        Thread pro = new Thread(new Producer(res));
        Thread con = new Thread(new Consumer(res));
        Thread pro1 = new Thread(new Producer(res));
        Thread con1 = new Thread(new Consumer(res));
        pro.start();
        con.start();
        pro1.start();
        con1.start();
    }
}