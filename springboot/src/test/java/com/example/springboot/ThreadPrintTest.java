package com.example.springboot;
/* 静态导包 */
import static java.lang.System.out;

/**
 * 多线程交替打印 1 - 100
 * @author Logan
 *
 */
public class ThreadPrintTest {
    // 创建变量
    int i = 1;

    public static void main(String[] args) {
        // 创建该类的对象
        ThreadPrintTest obj = new ThreadPrintTest();
        // 使用匿名内部类的形式，没创建runnable对象
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while(obj.i < 101){
                    // 上锁当前对象
                    synchronized(this) {
                        // 唤醒另一个线程
                        notify();
                        out.println("Thread " + Thread.currentThread().getName()  + " "+ obj.i ++);
                        try {
                            Thread.currentThread();
                            // 使其休眠100毫秒，放大线程差异
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            // 释放掉锁
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        // 启动多个线程（想创建几个就创建几个）
        new Thread(runnable).start();
        new Thread(runnable).start();
        /*new Thread(runnable).start();
        new Thread(runnable).start();*/

    }
}