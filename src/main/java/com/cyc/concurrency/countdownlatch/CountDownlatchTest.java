package com.cyc.concurrency.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch， 一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
 * Created by cyc_e on 2018/4/28.
 */
public class CountDownlatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(new readNum(i, countDownLatch)).start();
        }
        countDownLatch.await();
        System.out.println("线程执行结束。。。。");
    }

    static class readNum implements Runnable {
        private int id;
        private CountDownLatch latch;

        public readNum(int id, CountDownLatch latch) {
            this.id = id;
            this.latch = latch;
        }

        @Override
        public void run() {
            synchronized (this) {
                System.out.println("线程组任务" + id + "结束，其他任务继续");
                latch.countDown();
            }
        }
    }
}
