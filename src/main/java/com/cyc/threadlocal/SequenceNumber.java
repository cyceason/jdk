package com.cyc.threadlocal;

/**
 * ThreadLocal用法例子
 *
 * @author cyc_e
 */
public class SequenceNumber {
    public ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
        @Override
        public Integer initialValue() {
            return 0;
        }
    };

    public int getNextNum() {
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }

    public static void main(String[] args) {
        SequenceNumber sn = new SequenceNumber();
        new Thread(new TestClient(sn)).start();
        new Thread(new TestClient(sn)).start();
        new Thread(new TestClient(sn)).start();
    }

}

class TestClient implements Runnable {
    private SequenceNumber sn;

    public TestClient(SequenceNumber sn) {
        this.sn = sn;
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("当前线程 : " + Thread.currentThread() + " ;sn对象 :  " + sn + " ;sn字段 ：" + sn.seqNum + " : " + sn.getNextNum());
        }
    }
}