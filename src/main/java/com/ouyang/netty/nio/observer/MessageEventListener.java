package com.ouyang.netty.nio.observer;

public class MessageEventListener implements EventListener{
    @Override
    public void doEvent(ResultForEvent result) {
        System.out.println("发送了消息");
    }
}
