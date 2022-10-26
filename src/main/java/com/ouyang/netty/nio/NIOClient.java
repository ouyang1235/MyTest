package com.ouyang.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {

    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6666);
        socketChannel.configureBlocking(false);
        boolean connect = socketChannel.connect(address);
        if (!connect){
            while (!socketChannel.finishConnect()){
                System.out.println("连接服务器需要时间，可以做其他事情...");
            }
        }
        String msg = "hello java技术爱好者!";
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        socketChannel.write(buffer);
        System.in.read();
    }
}
