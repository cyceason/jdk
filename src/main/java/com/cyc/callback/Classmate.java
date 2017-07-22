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
        System.out.println(msg);
        System.out.println("my classmate hava executed the message by I");
        /** 执行回调 **/
        callBack.execute(" done ! "); // 这相当于同学执行完之后打电话给你
    }
}
