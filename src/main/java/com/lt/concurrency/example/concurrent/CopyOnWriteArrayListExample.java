package com.lt.concurrency.example.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by taoshiliu on 2018/4/30.
 * CopyOnWriteArrayList并发容器，线程安全
 * 是使用ReentrantLock实现线程安全
 *
 * 写操作时，复制一个新的List
 * 读操作，直接读原List
 */
@Slf4j
public class CopyOnWriteArrayListExample {

    public static int clinetTotal = 5000;

    public static int threadTotal = 200;

    private static List<Integer> list = new CopyOnWriteArrayList<>();

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
        log.info("size: {}" ,list.size());
    }

    private static void update(int i) {
        list.add(i);
    }
}
