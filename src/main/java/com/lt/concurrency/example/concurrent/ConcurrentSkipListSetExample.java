package com.lt.concurrency.example.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by taoshiliu on 2018/4/30.
 * ConcurrentSkipListSet并发容器，线程安全
 *
 * 本质依赖ConcurrentHashMap
 */
@Slf4j
public class ConcurrentSkipListSetExample {

    public static int clinetTotal = 5000;

    public static int threadTotal = 200;

    private static Set<Integer> set = new ConcurrentSkipListSet<>();

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
        log.info("size: {}" ,set.size());
    }

    private static void update(int i) {
        set.add(i);
    }
}
