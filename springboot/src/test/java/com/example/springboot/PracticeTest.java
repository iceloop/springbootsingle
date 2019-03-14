package com.example.springboot;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程练习：
 * 1.开启一个线程打印1～52，开启另一个线程打印A～Z
 * 打印方式：12A34B46C依次打印
 * @author yw-tony
 *
 */
public class PracticeTest {

    public static void main(String[] args){
        final DataPrint data = new DataPrint();
        //打印字母的线程(大家也可以把这两个线程抽取出来作为两个单独的类，这里为了实现简单我就直接写在main方法中了)
        new Thread(new Runnable(){
            @Override
            public void run() {
                while(data.letterFlag){
                    data.printLetter();
                }
            }
        }).start();

        //打印数字的线程
        new Thread(new Runnable(){
            @Override
            public void run() {
                while(data.numFlag){
                    data.printNun();
                }
            }
        }).start();

    }
    /**
     * 打印类
     * @author yw-tony
     *
     */
    static class DataPrint{
        public boolean letterFlag = true;//线程结束标记;
        public boolean numFlag = true;
        //数字的初始值
        int num = 1;
        //字母的初始值
        //这里A~Z的字母对应的阿拉伯数字为65～90，
        int letter = 65;
        //线程等待标记
        boolean flag = true;
        //java线程并发库中的锁相当与（synchronized）
        Lock lock = new ReentrantLock();
        //线程并发库中用于线程之间通讯的类相当于wait(),notify()
        Condition condLetter = lock.newCondition();
        Condition condNum = lock.newCondition();
        /**
         * 打印字字母的方法
         *
         */
        public void printLetter(){
            //如果打印到Z则结束线程并停止
            if(letter >= 90 ){
                letterFlag = false;
                return ;
            }
            //锁定代码块，锁定时其他线程不能访问其中内容
            lock.lock();
            try{
                if(flag){//如果执行打印数字的线程正在执行，则该线程进入等待状态
                    condLetter.await();
                }
                System.out.println(Thread.currentThread().getName()+":"+(char)letter);
                letter++;
                Thread.sleep(100);
                //打印执行完成，唤醒打印数字的线程
                flag = true;
                condNum.signal();
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                //解锁当前代码快
                lock.unlock();
            }

        }
        /**
         * 打印数字的方法
         */
        public void printNun(){
            //如果打印到52则结束线程并停止
            if(num>=52){
                numFlag = false;
                return;
            }
            lock.lock();
            try{
                if(!flag){
                    condNum.await();
                }
                System.out.println(Thread.currentThread().getName()+":"+num);
                num++;
                System.out.println(Thread.currentThread().getName()+":"+num);
                num++;
                Thread.sleep(100);
                flag = false;
                condLetter.signal();//唤醒打印字母的线程
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                lock.unlock();
            }
        }
    }
}