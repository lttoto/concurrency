package com.lt.concurrency.example.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by taoshiliu on 2018/4/29.
 * compareAndSet（except，future）表示当值为except时，将值更新为future
 */
@Slf4j
public class AtomicExample4 {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0,2);
        count.compareAndSet(0,1);
        count.compareAndSet(1,3);
        count.compareAndSet(2,4);
        count.compareAndSet(3,5);
        log.info("count:{}",count.get());

    }
}
