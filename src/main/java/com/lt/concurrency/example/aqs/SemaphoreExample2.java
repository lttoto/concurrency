package com.lt.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by taoshiliu on 2018/4/30.
 * semaphore.acquire(3)一个线程一次可以获取多少许可
 */
@Slf4j
public class SemaphoreExample2 {

    private final static int threadCount = 200;

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(20);

        for(int i = 0 ; i < threadCount;i++) {
            final int threadNum = i;
            //Thread.sleep(1);
            exec.execute(() -> {
                try {
                    semaphore.acquire(3);//获得许可
                    test(threadNum);
                    semaphore.release(3);//释放许可
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
