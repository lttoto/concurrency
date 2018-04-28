package com.lt.concurrency.example.atomic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by taoshiliu on 2018/4/29.
 */
@Slf4j
public class AtomicExample5 {

    private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");

    /*
    * 使用AtomicIntegerFieldUpdater时，需要设置的并发字段必须使用volatile修饰
    * */
    @Getter
    public volatile int count = 100;

    public static void main(String[] args) {

        AtomicExample5 example5 = new AtomicExample5();

        if(updater.compareAndSet(example5,100,120)) {
            log.info("update success 1, {}",example5.getCount());
        }

        if(updater.compareAndSet(example5,100,120)) {
            log.info("update success 2, {}",example5.getCount());
        }else {
            log.info("update failed, {}",example5.getCount());
        }
    }
}
