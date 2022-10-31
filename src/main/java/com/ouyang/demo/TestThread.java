package com.ouyang.demo;

public class TestThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            if(i % 2 == 0){
                //写法一
                Thread.yield();
                //写法二：由于该类继承了Thread类，所以该子类拥有yield()方法,可以直接使用yield()来释放当前线程
                //yield();
            }
        }
    }
}
