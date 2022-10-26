package com.ouyang.netty.socket;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class SocketClient {

    public static void main(String[] args) {
        sendDataByUDP();
    }

    private static void sendTCPMsg(){
        try{
            Socket socket = new Socket("localhost", 8088);

            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("在吗，我是客户端\n");
            pw.flush();

            socket.shutdownOutput();

            //接收从服务器的消息
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String info = null;
            while ((info = br.readLine())!= null){
                System.out.println("[服务端消息]"+info);
            }

            br.close();
            pw.close();


        }catch (Exception e){

        }
    }

    private static void sendDataByUDP(){
        try{
            String str = "发送UDP消息：你好啊！";
            InetAddress addr = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(str.getBytes(), str.getBytes().length, addr, 8088);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
