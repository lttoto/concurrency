package com.lt.concurrency.example.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by taoshiliu on 2018/4/30.
 * ConcurrentHashMap并发容器
 */
@Slf4j
public class ConcurrentHashMapExample {

    public static int clinetTotal = 5000;

    public static int threadTotal = 200;

    private static Map<Integer,Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clinetTotal);
        for(int i = 0;i < clinetTotal;i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                }catch (Exception e) {
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size: {}" ,map.size());
    }

    private static void update(int i) {
        map.put(i,i);
    }
}
