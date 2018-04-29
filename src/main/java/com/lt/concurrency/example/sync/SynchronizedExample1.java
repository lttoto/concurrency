package com.lt.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by taoshiliu on 2018/4/29.
 * synchronized修饰代码块和方法只作用于同一对象
 * 不同的调用对象是互相不影响的
 * 子类继承父类，无法继承synchronized关键字（synchronized没有继承性）（synchronized不属于方法声明的一部分）
 * 子类要实现synchronized需要自己声明
 */
@Slf4j
public class SynchronizedExample1 {

    //修饰一个代码块
    public void test1() {
        synchronized (this) {
            for(int i = 0;i < 10;i++) {
                log.info("test1 - {}",i);
            }
        }
    }

    //修饰一个方法
    public synchronized void test2() {
        for(int i = 0;i < 10;i++) {
            log.info("test2 - {}",i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();

        //synchronized修饰代码块、方法，只对于使用同一个对象的线程，能实现同步
        executorService.execute(() -> {
            example1.test1();
        });
        executorService.execute(() -> {
            example1.test1();
        });

        //synchronized修饰代码块、方法，只对于使用同一个对象的线程，能实现同步（反例）
        executorService.execute(() -> {
            example1.test1();
        });
        executorService.execute(() -> {
            example2.test1();
        });
    }
}
