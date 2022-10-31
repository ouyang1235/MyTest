package com.ouyang.demo.effective;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Chapter15 {
    private static final String[] STRINGS = {"1","2"};
    public static final List<String> VALUES = Collections.unmodifiableList(Arrays.asList(STRINGS));



    public static final String[] getStrings(){
        return STRINGS.clone();
    }

}
