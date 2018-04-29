package com.lt.concurrency.example.publish;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Created by taoshiliu on 2018/4/29.
 * 不安全的发布对象
 * 调用这可以修改该对象的私有变量
 */
@Slf4j
public class UnsafePublish {

    private String[] states = {"a","b","c"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "d";
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
    }
}
