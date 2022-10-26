package com.ouyang.netty.primary;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;
import java.util.Scanner;

public class MyClient {

    private Bootstrap bootstrap;

    private EventLoopGroup eventLoopGroup;

    public MyClient() {
        this.eventLoopGroup = new NioEventLoopGroup();
        this.bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.AUTO_READ,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        channel.pipeline()
                                .addLast(new LineBasedFrameDecoder(1024))
                                .addLast(new StringDecoder(Charset.forName("GBK")))
                                .addLast(new StringEncoder(Charset.forName("GBK")))
                                .addLast(new MyClientHandler());
                    }
                });
    }

    public static void main(String[] args) {
        MyClient myClient = new MyClient();
        myClient.connect("127.0.0.1",6666);
    }

    private void connect(String inetHost,int port){
        try{
            ChannelFuture channelFuture = bootstrap.connect(inetHost, port).sync();
            channelFuture.addListener((ChannelFutureListener) channelFuture1 -> {
                boolean success = channelFuture1.isSuccess();
                System.out.println("连接状态:" + (success ? "成功" : "失败"));
                if (success){
                    new Thread(()->{
                        Scanner scanner = new Scanner(System.in);
                        while (scanner.hasNextLine()){
                            String msg = scanner.nextLine();
                            if ("exit".equals(msg)){
                                exit(channelFuture1);
                            }
                            channelFuture1.channel().writeAndFlush(msg + "\r\n");
                        }
                    }).start();
                }
            });
            channelFuture.channel().closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }

    }

    private void exit(ChannelFuture channelFuture){
        channelFuture.channel().close();
        System.out.println("主动断开连接...");
    }


}
