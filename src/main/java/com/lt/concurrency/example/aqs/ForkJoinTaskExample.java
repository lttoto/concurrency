package com.lt.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.function.Function;

/**
 * Created by taoshiliu on 2018/4/30.
 */
@Slf4j
public class ForkJoinTaskExample extends RecursiveTask<Integer>{

    public static final int threshold = 2;
    private int start;
    private int end;

    public ForkJoinTaskExample(int start,int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        boolean canCompute = (end - start) <= threshold;
        if(canCompute) {
            for (int i = start; i <= end;i++) {
                sum += i;
            }
        }else {
            int middle = (start + end) / 2;
            ForkJoinTaskExample leftTask = new ForkJoinTaskExample(start,middle);
            ForkJoinTaskExample rightTask = new ForkJoinTaskExample(middle+1,end);

            leftTask.fork();
            rightTask.fork();

            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        ForkJoinTaskExample task = new ForkJoinTaskExample(1,100);

        Future<Integer> result = forkJoinPool.submit(task);

        try {
            log.info("result:{}",result.get());
        }catch (Exception e) {
            log.error("exception",e);
        }
    }
}
