package com.cyc.five.threadpool.notfix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 可变尺寸的线程池
 * 
 * @author Administrator
 *
 */
public class NotFixedThreadPoolTest {
	public static void main(String[] args) {
		// 创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们。
		ExecutorService pool = Executors.newCachedThreadPool();
		// 将线程放入池中进行执行
		pool.execute(new MyThread());
		pool.execute(new MyThread());
		pool.execute(new MyThread());
		pool.execute(new MyThread());
		pool.execute(new MyThread());
		// 必须关闭线程池
		pool.shutdown();
	}

}

class MyThread extends Thread {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "正在执行。。。");
	}
}
