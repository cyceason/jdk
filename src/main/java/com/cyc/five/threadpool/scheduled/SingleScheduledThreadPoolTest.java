package com.cyc.five.threadpool.scheduled;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 单任务延迟连接池
 * 
 * @author Administrator
 *
 */
public class SingleScheduledThreadPoolTest {
	public static void main(String[] args) {
		// 创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行。
		ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();
		// 将线程放入池中进行执行
		pool.execute(new MyThread());
		pool.execute(new MyThread());
		pool.execute(new MyThread());
		// 使用延迟执行风格的方法
		pool.schedule(new MyThread(), 1000, TimeUnit.MILLISECONDS);
		pool.schedule(new MyThread(), 1000, TimeUnit.MILLISECONDS);
		// 必须关闭线程池
		pool.shutdown();
	}

}
