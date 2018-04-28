package com.lt.concurrency.example.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by taoshiliu on 2018/4/29.
 */
@Slf4j
public class AtomicExample6 {

    private static AtomicBoolean isHappened = new AtomicBoolean(false);

    //总请求数
    public static int clinetTotal = 5000;

    //同时并发执行的线程数
    public static int threadTotal = 200;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clinetTotal);
        for(int i = 0;i < clinetTotal;i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                }catch (Exception e) {
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",isHappened.get());
    }

    private static void test() {
        if(isHappened.compareAndSet(false,true)) {
            log.info("execute");
        }
    }
}
