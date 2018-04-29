package com.lt.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by taoshiliu on 2018/4/30.
 * CountDownLatch同步组件
 */
@Slf4j
public class CoutDownLatchExample1 {

    private final static int threadCount = 200;

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();

        final CountDownLatch coutDownLatch = new CountDownLatch(threadCount);

        for(int i = 0 ; i < threadCount;i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    test(threadNum);
                }catch (Exception e) {
                    log.error("exception",e);
                }finally {
                    coutDownLatch.countDown();
                }
            });
        }
        coutDownLatch.await();
        log.info("finish");
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception{
        Thread.sleep(100);
        log.info("{}",threadNum);
        Thread.sleep(100);
    }
}
