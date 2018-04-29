package com.lt.concurrency.example.singleton;

/**
 * Created by taoshiliu on 2018/4/29.
 * 线程安全的饿汉模式
 * 使用synchronized保证线程安全
 * 同一时刻只能一个线程使用对象，性能极差
 */
public class SingletonExample3 {

    //私有构造函数
    private SingletonExample3() {

    }
    //单例对象
    private static SingletonExample3 instance = null;

    //静态的工厂方法
    public static synchronized SingletonExample3 getInstance() {
        if(instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }
}
