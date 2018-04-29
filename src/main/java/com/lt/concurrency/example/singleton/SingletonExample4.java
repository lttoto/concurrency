package com.lt.concurrency.example.singleton;

/**
 * Created by taoshiliu on 2018/4/29.
 * 线程不安全
 * 双重检测加同步锁的饿汉模式
 * 线程不安全的原因（CPU指令重排序）
 *
 */
public class SingletonExample4 {

    //私有构造函数
    private SingletonExample4() {

    }
    //单例对象
    private static SingletonExample4 instance = null;

    //静态的工厂方法
    public static  SingletonExample4 getInstance() {
        if(instance == null) {
            //双重检测机制
            //同步锁
            synchronized (SingletonExample4.class) {
                if(instance == null) {
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }
}
