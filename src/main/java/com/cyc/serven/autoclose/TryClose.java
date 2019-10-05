package com.cyc.serven.autoclose;

/**
 * 不知道怎么测试，打印不出来
 * 
 * @author cyc_e
 *
 */
public class TryClose implements AutoCloseable {

	@Override
	public void close() throws Exception {
		System.out.println(" Custom close method close resources ");
	}

	public static void main(String[] args) throws Throwable {
		TryClose tryClose = new TryClose();
		System.out.println(tryClose);
	}
}
