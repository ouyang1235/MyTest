package com.ouyang.demo.netty.nio.observer;

public class MessageEventListener implements EventListener{
    @Override
    public void doEvent(ResultForEvent result) {
        System.out.println("发送了消息");
    }
}
