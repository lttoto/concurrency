package com.lt.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by taoshiliu on 2018/4/29.
 * synchronized修饰静态代码块和类作用于类
 */
@Slf4j
public class SynchronizedExample2 {

    //修饰一个类
    public void test1() {
        synchronized (SynchronizedExample2.class) {
            for(int i = 0;i < 10;i++) {
                log.info("test1 - {}",i);
            }
        }
    }

    //修饰一个静态方法
    public static synchronized void test2() {
        for(int i = 0;i < 10;i++) {
            log.info("test2 - {}",i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();

        //synchronized修饰静态代码块和类，将作用于这个类下的所有对象
        executorService.execute(() -> {
            example1.test1();
        });
        executorService.execute(() -> {
            example2.test1();
        });
    }
}
