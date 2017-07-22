package com.cyc.callback;

public class Classmate {
    /**
     * 处理消息
     *
     * @param msg      接收的消息
     * @param callBack 回调函数处理类
     */
    public void executeMessage(String msg, CallBack callBack) {

        /** 处理消息 **/
        System.out.println("同学收到我的消息 ：" + msg);
        /** 执行回调 **/
        callBack.execute("同学处理完毕我的事情");
    }
}
