package com.cyc.five.threadpool.fix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 固定线程池例子
 * 
 * @author Administrator
 *
 */
public class FixedThreadPoolTest {
	public static void main(String[] args) {
		// 创建一个可重用固定线程数的线程池
		ExecutorService pool = Executors.newFixedThreadPool(2);
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
