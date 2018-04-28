package com.lt.concurrency.example.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by taoshiliu on 2018/4/15.
 * AtomicLong的效率低于LongAdder
 * AtomicLong是绝对的安全
 * Atomic的重要方法getAndIncrement()，incrementAndGet()
 * compareAndSet(except，update)标志位更改、表示只有一个线程可以将false/true转换，
 *      先判断目前是否位except，若是=>update成【update】
 *                            若不是不能更新
*       所以当一个线程更新完成后，别的线程都不能更新
 *      从而保证的只有一个线程更新成功
 */
@Slf4j
public class AtomicExample3 {

    public static int clinetTotal = 5000;

    public static int threadTotal = 200;

    public static LongAdder count = new LongAdder();

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clinetTotal);
        for(int i = 0;i < clinetTotal;i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                }catch (Exception e) {
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",count);
    }

    private static void add() {
        count.increment();
        //count.getAndIncrement();
    }
}
