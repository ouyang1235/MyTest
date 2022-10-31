package com.ouyang.demo.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class GroupTalkServer {

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public GroupTalkServer() {
        try{
            this.serverSocketChannel = ServerSocketChannel.open();
            InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6666);
            this.selector = Selector.open();
            serverSocketChannel.bind(address);
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void start(){
        try{
            while (true){
                int i = selector.select(2000);
                if (i > 0){
                    Set<SelectionKey> set = selector.selectedKeys();
                    Iterator<SelectionKey> it = set.iterator();
                    while(it.hasNext()){
                        SelectionKey key = it.next();
                        if (key.isAcceptable()){
                            SocketChannel channel = serverSocketChannel.accept();
                            channel.configureBlocking(false);
                            channel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                            System.out.println(channel.getRemoteAddress() + "已上线!");
                        }else if (key.isReadable()){
                            read(key);
                        }
                        it.remove();
                    }
                }else {
                    System.out.println("等待2s，继续等待中....");
                }
            }

        }catch (Exception e){

        }
    }

    private void read(SelectionKey key){
        SocketChannel socketChannel = null;
        try{
            ByteBuffer buffer =ByteBuffer.allocate(1024);
            socketChannel = (SocketChannel) key.channel();
            int count = socketChannel.read(buffer);
            if (count > 0){
                String msg = new String(buffer.array());
                System.out.println("from 客户端:" + msg);
                sendMsgToAllClient(msg,socketChannel);
                buffer.clear();
            }
        }catch (Exception e){

        }
    }

    private void sendMsgToAllClient(String msg,SocketChannel socketChannel) throws Exception{
        System.out.println("服务器转发消息中...,msg:"+ msg);
        Set<SelectionKey> keys = selector.keys();
        Iterator<SelectionKey> it = keys.iterator();
        while(it.hasNext()){
            SelectionKey key = it.next();
            ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
            Channel channel = key.channel();
            if (channel instanceof SocketChannel && !channel.equals(socketChannel)){
                SocketChannel channel1 = (SocketChannel) channel;
                channel1.write(buffer);
            }
        }
    }

    public static void main(String[] args) {
        GroupTalkServer groupTalkServer = new GroupTalkServer();
        groupTalkServer.start();
    }

}
