package com.ouyang.netty.io;

import java.io.*;

public class CopyAndWriteTest {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\admin\\Desktop\\随笔.txt");
        File out = new File("C:\\Users\\admin\\Desktop\\随笔-copy.txt");
        try{
            FileReader fileInputStream = new FileReader(file);
            char[] buf = new char[128];
            BufferedReader inputStream = new BufferedReader(fileInputStream);
            BufferedWriter outStream = new BufferedWriter(new FileWriter(out));
            int len;
            while ((len = inputStream.read(buf)) > 0){
                System.out.println(len);
                outStream.write(buf,0,len);
            }
            inputStream.close();
            outStream.close();
        }catch (Exception e){

        }

    }
}
