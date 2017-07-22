package com.cyc.callback;

public class I implements CallBack, Runnable {
    private Classmate classmate;

    /**
     * 发送出去的消息
     */
    private String message;

    public I(Classmate classmate, String message) {
        super();
        this.classmate = classmate;
        this.message = message;
    }

    /**
     * 发送消息
     */
    public void sendMessage() {
        /** 创建一个新的线程发送消息 **/
        Thread thread = new Thread(this);
        System.out.println(thread.getName());
        thread.start();
        /** 当前线程继续执行 **/
        System.out.println("I send a message to my classmate");
    }

    /**
     * 发送消息后的回调函数
     */
    public void execute(Object... objects) {
        /** 打印返回的消息 **/
        System.out.println(objects[0]);
        /** 打印发送消息的线程名称 **/
        System.out.println(Thread.currentThread().getName());
        /** 中断发送消息的线程 **/
        Thread.interrupted();
    }

    public static void main(String[] args) {
        I i = new I(new Classmate(), "Hello");
        i.sendMessage();
    }

    public void run() {
        classmate.executeMessage(message, this); // 这相当于给同学打电话，打完电话之后，这个线程就可以去做其他事情了，只不过等到你的同学打回电话给你的时候你要做出响应
    }
}
