package com.ouyang.demo.netty.primary;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class MyClientOutHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("出站适配器已监听到消息");
        super.read(ctx);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("出站适配器已监听到出站消息");
        ctx.writeAndFlush("劫持消息 \r\n");
        super.write(ctx, msg, promise);
    }

}
