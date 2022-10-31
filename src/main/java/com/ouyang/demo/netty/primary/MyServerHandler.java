package com.ouyang.demo.netty.primary;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;

import java.nio.charset.StandardCharsets;

public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "已上线！");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "已下线！");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("msg from:"+ctx.channel().remoteAddress() +",content:"+msg);
//        if ("hello".equals(msg)){
//            ctx.channel().writeAndFlush("你好呀~ 欢迎来到米奇妙妙屋！ \r\n");
//        }else {
//            ctx.channel().writeAndFlush("服务器已收到消息~ \r\n");
//        }
        handlerHttpRequest(ctx,msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }


    private void handlerHttpRequest(ChannelHandlerContext ctx, Object msg){
        if(msg instanceof HttpRequest){
            DefaultHttpRequest request = (DefaultHttpRequest) msg;
            System.out.println("URI:"+request.uri());
            System.out.println(msg);
        }

        if (msg instanceof HttpContent){
            LastHttpContent httpContent = (LastHttpContent) msg;
            ByteBuf byteData = httpContent.content();
            if(!(byteData instanceof EmptyByteBuf)){
                byte[] msgByte = new byte[byteData.readableBytes()];
                byteData.readBytes(msgByte);
                System.out.println(new String(msgByte, StandardCharsets.UTF_8));
            }
        }

        String sendMsg = "hello!!!welcome!!!!";
        DefaultFullHttpResponse resp = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.wrappedBuffer(sendMsg.getBytes())
        );
        resp.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=UTF-8");
        resp.headers().set(HttpHeaderNames.CONTENT_LENGTH,resp.content().readableBytes());
        resp.headers().set(HttpHeaderNames.CONNECTION,HttpHeaderValues.KEEP_ALIVE);
        ctx.write(resp);
        ctx.flush();
    }
}
