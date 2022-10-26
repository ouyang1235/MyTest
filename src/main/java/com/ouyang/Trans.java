package com.ouyang;

import java.io.File;

public class Trans {

    public static void main(String[] args) {

        String path = "D:\\ouyang\\公司文档\\bbd-enterprise-sendex-ding-client";
        modify(path);
    }

    public static void modify(String path){
        File file = new File(path);
        File[] files = file.listFiles();

        for (File child : files) {
            String absolutePath = child.getAbsolutePath();
            if (child.exists()){
                if (child.isDirectory()){
                    modify(absolutePath);
                }else {
                    String replace="";
                    if (absolutePath.contains(".acss")){
                        replace = absolutePath.replace(".acss", ".ttss");
                    }
                    if (absolutePath.contains(".axml")){
                        replace = absolutePath.replace(".axml", ".ttml");
                    }
                    if (!"".equals(replace)){
                        child.renameTo(new File(replace));
                    }
                }
            }

        }


    }





}
