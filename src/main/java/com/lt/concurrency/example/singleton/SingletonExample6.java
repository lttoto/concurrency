package com.lt.concurrency.example.singleton;

/**
 * Created by taoshiliu on 2018/4/29.
 * 饿汉模式-静态代码块方式（一定要注意代码顺序）
 * 私有变量必须在静态代码块之前
 */
public class SingletonExample6 {

    //私有构造函数
    private SingletonExample6() {

    }

    //单例对象
    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }

    //静态的工厂方法
    public static SingletonExample6 getInstance() {
        return instance;
    }

}
