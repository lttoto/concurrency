package com.lt.concurrency.example.threadLocal;

/**
 * Created by taoshiliu on 2018/4/29.
 * ThreadLocal内部维护一个Map<key,value>;key=ThreadId,value=定义的值
 *
 */
public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }

    //保证内存不溢出
    public static void remove() {
        requestHolder.remove();
    }
}
