package com.ouyang;

public class Father {

    public Father() {
        Class<?> aClass = super.getClass();
        System.out.println(aClass.getName());
    }
}
