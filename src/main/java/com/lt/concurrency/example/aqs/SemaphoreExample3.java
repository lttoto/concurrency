package com.lt.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by taoshiliu on 2018/4/30.
 * semaphore.tryAcquire()尝试获取许可，可以配置尝试次数、尝试时间
 */
@Slf4j
public class SemaphoreExample3 {

    private final static int threadCount = 200;

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(20);

        for(int i = 0 ; i < threadCount;i++) {
            final int threadNum = i;
            //Thread.sleep(1);
            exec.execute(() -> {
                try {
                    //尝试获取许可
                    if(semaphore.tryAcquire(3)) {
                        test(threadNum);
                        semaphore.release();//释放许可
                    }
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
