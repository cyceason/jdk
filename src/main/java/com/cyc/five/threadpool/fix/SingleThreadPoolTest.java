package com.cyc.five.threadpool.fix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单个线程池
 * 
 * @author Administrator
 *
 */
public class SingleThreadPoolTest {

	public static void main(String[] args) {
		// 创建一个可重用固定线程数的线程池
		ExecutorService pool = Executors.newSingleThreadExecutor();
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
