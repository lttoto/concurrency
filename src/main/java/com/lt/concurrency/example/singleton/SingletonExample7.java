package com.lt.concurrency.example.singleton;

/**
 * Created by taoshiliu on 2018/4/29.
 * 使用枚举来实现单例模式
 * 由JVM保证线程安全
 * （推荐）原因->比饿汉模式高效，不在类装载是创建实例
 *           ->比懒汉模式安全，JVM保障线程安全
 */
public class SingletonExample7 {
    //私有构造函数
    private SingletonExample7() {

    }

    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private SingletonExample7 singleton;

        //JVM保证代码只执行一次
        Singleton() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance() {
            return singleton;
        }
    }
}
