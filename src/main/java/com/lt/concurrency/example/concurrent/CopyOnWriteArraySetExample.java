package com.lt.concurrency.example.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by taoshiliu on 2018/4/30.
 * CopyOnWriteArraySet并发容器，线程安全
 * 批量操作，无法保证线程安全
 *
 * 批量操作需要手动加锁
 */
@Slf4j
public class CopyOnWriteArraySetExample {

    public static int clinetTotal = 5000;

    public static int threadTotal = 200;

    private static Set<Integer> set = new CopyOnWriteArraySet<>();

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
