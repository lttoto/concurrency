package com.lt.concurrency.example.syncContainer;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by taoshiliu on 2018/4/30.
 * 循环删除的场景
 * 使用foreach、Iterator两种方法会出现ConcurrentModificationException
 * for循环不会出现该报错
 *
 * 解决方案，遍历的过程不操作集合
 *
 * 同步容器可以解决线程安全的问题
 *        在并发场景下会出现问题
 *
 *        增删改同步容器时，不能遍历同步容器（并发时会出现这个场景）
 * 同步容器性能较差
 */
public class VectorExample3 {

    private static void test1(Vector<Integer> v1) {
        for(Integer i : v1) {
            if(i.equals(3)) {
                v1.remove(i);
            }
        }
    }

    private static void test2(Vector<Integer> v1) {
        Iterator<Integer> iterator = v1.iterator();
        while(iterator.hasNext()) {
            Integer i = iterator.next();
            if(i.equals(3)) {
                v1.remove(i);
            }
        }
    }

    private static void test3(Vector<Integer> v1) {
        for(int i = 0;i < v1.size();i++) {
            if(v1.get(i).equals(3)) {
                v1.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        test1(vector);
        test2(vector);
        test3(vector);
    }
}
