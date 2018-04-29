package com.lt.concurrency.example.commonUnsafe;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by taoshiliu on 2018/4/29.
 * 使用对象封闭解决线程不安全的问题
 */
@Slf4j
public class DateFormatExample2 {

    //private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    public static int clinetTotal = 5000;

    public static int threadTotal = 200;

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clinetTotal);
        for(int i = 0;i < clinetTotal;i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                }catch (Exception e) {
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }

    private static void update() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            simpleDateFormat.parse("20180208");
        }catch (Exception e) {
            log.error("parse exception",e);
        }
    }
}
