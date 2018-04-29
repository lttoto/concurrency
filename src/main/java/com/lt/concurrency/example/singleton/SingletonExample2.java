package com.lt.concurrency.example.singleton;

/**
 * Created by taoshiliu on 2018/4/29.
 * 饿汉模式
 * 单例实例在类装载时进行创建
 * 线程安全、性能较差
 * 若实例创建过程特别繁琐，耗时较大，会导致类装载太消耗资源
 */
public class SingletonExample2 {

    //私有构造函数
    private SingletonExample2() {

    }
    //单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    //静态的工厂方法
    public static SingletonExample2 getInstance() {
        return instance;
    }
}
