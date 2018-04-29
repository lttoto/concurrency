package com.lt.concurrency.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Interner;

/**
 * Created by taoshiliu on 2018/4/29.
 * 使用guava的不可变模式
 */
public class ImmutableExample3 {

    private final static ImmutableList list = ImmutableList.of(1,2,3);

    private final static ImmutableSet set = ImmutableSet.copyOf(list);

    private final static ImmutableMap<Integer,Integer> map = ImmutableMap.of(1,2,3,4);

    private final static ImmutableMap<Integer,Integer> map2  = ImmutableMap.<Integer,Integer>builder().put(1,2).put(3,4).put(5,6).build();

    public static void main(String[] args) {
        list.add(4);//会报错
    }
}
