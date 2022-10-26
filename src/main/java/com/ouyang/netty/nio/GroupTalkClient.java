package com.ouyang.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class GroupTalkClient {


    private Selector selector;

    private SocketChannel socketChannel;

    private String userName;

    public GroupTalkClient() {
        try {
            this.selector = Selector.open();
            this.socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",6666));
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            userName = socketChannel.getLocalAddress().toString().substring(1);
            System.out.println(userName + "is ok~");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void sendMsg(String msg){
        msg = userName + "说:" + msg;
        try{
            socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        }catch (Exception e){

        }
    }

    private void readMsg(){
        try{
            int count = selector.select();
            if (count > 0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if (key.isReadable()){
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        channel.read(buffer);
                        System.out.println(new String(buffer.array()));
                    }
                    iterator.remove();
                }
            }
        }catch (Exception e){

        }

    }

    public static void main(String[] args) {
        GroupTalkClient client = new GroupTalkClient();
        new Thread(()->{
            while (true){
                client.readMsg();
                try{
                    Thread.sleep(3000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String msg = scanner.nextLine();
            client.sendMsg(msg);
        }
    }

}
