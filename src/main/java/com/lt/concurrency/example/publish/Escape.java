package com.lt.concurrency.example.publish;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by taoshiliu on 2018/4/29.
 * 发布逸出
 * 线程不安全的写法
 */
@Slf4j
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape() {
        new InnerClass();
    }

    private class InnerClass {
        public InnerClass() {
            log.info("{}",Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
