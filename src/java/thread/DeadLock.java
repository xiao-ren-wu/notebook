package com.xrw.thread;

/**
 * @Created with IDEA
 * @author:Yuwenbo
 * @Date:2018/3/18
 * @Time:16:42
 * @JDK versions: 1.8.0_101
 * @apiNote :死锁示例---->两个线程分别持有对方想要的资源，有想获得对方的资源
 */

/**
 * 锁
 */
class MyLock {
    public static final Object locka = new Object();
    public static final Object lockb = new Object();
}

/**
 * 死锁示例
 */
class DeadLock implements Runnable {
    private boolean flag;

    public DeadLock(boolean arg) {
        flag = arg;
    }

    @Override
    public void run() {
        if (flag) {
            synchronized (MyLock.locka) {
                System.out.println("if-----locka");
                synchronized (MyLock.lockb) {
                    System.out.println("if-------lockb");
                }
            }
        } else {
            synchronized (MyLock.lockb) {
                System.out.println("else------lockb");
                synchronized (MyLock.locka) {
                    System.out.println("else-------locka");
                }
            }
        }
    }
}

class Demo {
    public static void main(String[] args) {
        DeadLock d1 = new DeadLock(true);
        Thread t1 = new Thread(d1);
        t1.start();
        DeadLock d2 = new DeadLock(false);
        Thread t2 = new Thread(d2);

        t2.start();
    }
}