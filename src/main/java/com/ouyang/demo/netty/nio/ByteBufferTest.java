package com.ouyang.demo.netty.nio;



import com.ouyang.demo.netty.CommonPath;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class ByteBufferTest {


    public static void main(String[] args) throws Exception {
        scatteredReadAndAggregatedWrites();
    }

    public static void scatteredReadAndAggregatedWrites() throws Exception{
        ByteBuffer buffer1 = ByteBuffer.allocate(5);
        ByteBuffer buffer2 = ByteBuffer.allocate(5);
        ByteBuffer buffer3 = ByteBuffer.allocate(5);
        ByteBuffer[] buffers = {buffer1, buffer2, buffer3};
        File file = new File("C:\\Users\\admin\\Desktop\\1.txt");
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\admin\\Desktop\\2.txt"));
        FileChannel ic = fis.getChannel();
        FileChannel oc = fos.getChannel();
        long read;
        long sumLength = 0;
        while((read = ic.read(buffers)) != -1){
            sumLength += read;
            System.out.println(sumLength);
            Arrays.stream(buffers)
                    .map(buffer -> "position=" + buffer.position() + ",limit=" + buffer.limit())
                    .forEach(System.out::println);
            Arrays.stream(buffers).forEach(Buffer::flip);
            oc.write(buffers);
            Arrays.stream(buffers).forEach(Buffer::clear);
        }

        System.out.println("总长度" + sumLength);
        fos.close();
        fis.close();
        oc.close();
        ic.close();

    }

    public static void testSocket(){
        try{
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6666);
            serverSocketChannel.bind(address);
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while(true){
                SocketChannel socketChannel = serverSocketChannel.accept();
                while (socketChannel.read(byteBuffer) != -1){
                    System.out.println(new String(byteBuffer.array()));
                    byteBuffer.clear();
                }
            }

        }catch (Exception e){

        }


    }


    public static void testCopyPaste(){
        try{
            File file = new File(CommonPath.TXT_IN_PATH);
            FileInputStream fis = new FileInputStream(file);
            FileChannel isc = fis.getChannel();

            FileOutputStream fos = new FileOutputStream(new File(CommonPath.TXT_OUT_PATH));
            FileChannel osc = fos.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate((int)file.length());
            isc.read(buffer);
            buffer.flip();
            osc.write(buffer);
            fos.close();
            fis.close();
            osc.close();
            isc.close();

        }catch (Exception e){

        }



    }



    public static void testByteBufferArray(){
        String msg = "啊哈哈哈哈，鸡汤来咯";
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(msg.getBytes());
        buffer.flip();
        byte[] arr = new byte[1024];
        int i=0;
        while(buffer.hasRemaining()){
            arr[i++] = buffer.get();
        }
        System.out.println(new String(arr));
    }

}
