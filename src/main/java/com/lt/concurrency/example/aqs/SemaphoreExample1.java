package com.lt.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Created by taoshiliu on 2018/4/30.
 * Semaphore信号量
 * 同一时间可以允许的线程数
 */
@Slf4j
public class SemaphoreExample1 {

    private final static int threadCount = 200;

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(20);

        for(int i = 0 ; i < threadCount;i++) {
            final int threadNum = i;
            //Thread.sleep(1);
            exec.execute(() -> {
                try {
                    semaphore.acquire();//获得许可
                    test(threadNum);
                    semaphore.release();//释放许可
                }catch (Exception e) {
                    log.error("exception",e);
                }
            });
        }
        log.info("finish");
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception{
        log.info("{}",threadNum);
        Thread.sleep(1000);
    }
}
