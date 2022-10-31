package com.ouyang.demo.netty.socket;

import java.io.*;
import java.net.*;

public class SocketServer {

    public static void main(String[] args) {

        getUDPMsg();

    }

    private static void getTCPMsg(){
        try{
            ServerSocket serverSocket = new ServerSocket(8088);

            Socket socket;


            while (true){
                socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(isr);
                String info = null;
                InetAddress addr = socket.getInetAddress();

                while((info = br.readLine()) != null){
                    System.out.println("[收取到消息] from ip:"+addr.getHostAddress()+",msg:"+info);
                }
                socket.shutdownInput();

                OutputStream outputStream = socket.getOutputStream();
                PrintWriter ps = new PrintWriter(outputStream);
                ps.write("welcom!");
                ps.flush();
                socket.shutdownOutput();
            }

        }catch (Exception e){

        }
    }


    private static void getUDPMsg(){
        try{
            byte[] bytes = new byte[1024];
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
            DatagramSocket socket = new DatagramSocket(8088);
            socket.receive(packet);
            String msg = new String(packet.getData(), 0, packet.getLength());
            System.out.println(msg);
            socket.close();

        }catch (Exception e){
            e.printStackTrace();
        }


    }


}
