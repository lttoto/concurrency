package com.lt.concurrency.example.immutable;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by taoshiliu on 2018/4/29.
 * final修饰普通变量时，不允许修改变量的值
 * final修饰引用变量时，不允许修改变量的引用，但是可以修改引用变量的值
 * private final static Map<Integer,Integer> map = Maps.newHashMap();容易引发线程安全问题
 */
public class ImmutableExample1 {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer,Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
    }

    public static void main(String[] args) {
        //map = Maps.newHashMap(); 会报错
        map.put(1,3);//修改引用变量的值,不会报错

    }

    private void test(final int a) {
        //a = 1;final修饰形式参数，之后也不能修改
    }
}
