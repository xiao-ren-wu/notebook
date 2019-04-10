package com.xrw.thread;


/**
 * @Created with IDEA
 * @author:Yuwenbo
 * @Date:2018/3/19
 * @Time:18:11
 * @JDK versions: 1.8.0_101
 * @apiNote 交替打印AB
 */
public class Print {
    private boolean flag=false;

    public synchronized void printA(){
        while(flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("A");
        flag=true;
        this.notify();
    }
    public synchronized void printB(){
        while(!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("B");
        flag=false;
        this.notify();
    }
}
class A implements Runnable{
    private Print p;

    public A(Print p) {
        this.p = p;
    }

    @Override
    public void run() {
        while (true){
            p.printA();
        }
    }
}
class B implements Runnable{
    public Print p;

    public B(Print p) {
        this.p = p;
    }

    @Override
    public void run() {
        while(true){
            p.printB();
        }
    }
}
class PrintAandBDemo{
    public static void main(String[] args) {
        Print p = new Print();
        Thread pa = new Thread(new A(p));
        Thread pb = new Thread(new B(p));
        pa.start();
        pb.start();
    }
}