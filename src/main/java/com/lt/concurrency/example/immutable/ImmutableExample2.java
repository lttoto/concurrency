package com.lt.concurrency.example.immutable;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

/**
 * Created by taoshiliu on 2018/4/29.
 * 使用java.util.Collections.unmodifiableXXX(XXX xxx)实现不可变变量
 * 上述情况下修改引用变量的值，会抛出java.lang.UnsupportedOperationException报错
 * unmodifiableXXX会创建一个新的Map，将所有的修改方法直接返回一个报错
 */
public class ImmutableExample2 {

    private static Map<Integer,Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1,3);//修改引用变量的值,会抛出java.lang.UnsupportedOperationException报错

    }
}
