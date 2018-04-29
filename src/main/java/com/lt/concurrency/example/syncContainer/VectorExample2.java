package com.lt.concurrency.example.syncContainer;

import lombok.extern.slf4j.Slf4j;

import java.util.Vector;

/**
 * Created by taoshiliu on 2018/4/30.
 * Vector线程安全，但是会出现线程越界的问题
 */
@Slf4j
public class VectorExample2 {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        for(int i = 0; i< 10;i++) {
            vector.add(i);
        }

        Thread thread1 = new Thread() {
            public void run() {
                for(int i = 0; i< 10;i++) {
                    vector.remove(i);
                }
            }
        };

        Thread thread2 = new Thread() {
            public void run() {
                for(int i = 0; i< 10;i++) {
                    vector.get(i);
                }
            }
        };
        thread1.start();
        thread2.start();
    }
}
