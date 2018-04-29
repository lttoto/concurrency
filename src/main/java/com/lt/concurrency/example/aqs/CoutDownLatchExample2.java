package com.lt.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Created by taoshiliu on 2018/4/30.
 * CountDownLatch同步组件
 * coutDownLatch.await(10, TimeUnit.MILLISECONDS);当超过时间阀值，不在阻塞主线程
 */
@Slf4j
public class CoutDownLatchExample2 {

    private final static int threadCount = 200;

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();

        final CountDownLatch coutDownLatch = new CountDownLatch(threadCount);

        for(int i = 0 ; i < threadCount;i++) {
            final int threadNum = i;
            //Thread.sleep(1);
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
        coutDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info("finish");
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception{
        Thread.sleep(100);
        log.info("{}",threadNum);
    }
}
