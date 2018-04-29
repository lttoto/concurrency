package com.lt.concurrency.example.singleton;

/**
 * Created by taoshiliu on 2018/4/29.
 * 高效线程安全的懒汉单例模式（volatile + 双重检测 + 同步锁）
 * volatile可以保证代码重排导致的线程不安全
 */
public class SingletonExample5 {

    //私有构造函数
    private SingletonExample5() {

    }
    //单例对象
    private volatile static SingletonExample5 instance = null;

    //静态的工厂方法
    public static  SingletonExample5 getInstance() {
        if(instance == null) {
            //双重检测机制
            //同步锁
            synchronized (SingletonExample5.class) {
                if(instance == null) {
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }
}
