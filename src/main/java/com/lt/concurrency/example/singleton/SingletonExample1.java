package com.lt.concurrency.example.singleton;

/**
 * Created by taoshiliu on 2018/4/29.
 * 懒汉模式
 * 单例实例在第一次使用是进行创建
 * 存在线程不安全的问题
 * 当两个线程同时获取instance并判断为null
 * 于是同时创建了两个SingletonExample1对象
 */
public class SingletonExample1 {

    //私有构造函数
    private SingletonExample1() {

    }
    //单例对象
    private static SingletonExample1 instance = null;

    //静态的工厂方法
    public static SingletonExample1 getInstance() {
        if(instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}
