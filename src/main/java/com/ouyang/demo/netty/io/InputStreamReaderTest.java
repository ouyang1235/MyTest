package com.ouyang.demo.netty.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InputStreamReaderTest {

    public static void main(String[] args) {
        try{
            InputStreamReader redaer = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(redaer);
            String buffer =null;
            while((buffer = bufferedReader.readLine())!=null){
                if ("exit".equals(buffer)){
                    System.exit(1);
                }
                System.out.println("输入内容:"+buffer);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }
}
