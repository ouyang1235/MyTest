package com.ouyang.rpc.impl;

import com.ouyang.rpc.interfaces.AobingService;

public class AobingServiceImpl implements AobingService {
    @Override
    public String hello(String name) {
        return "hello!I am"+name;

    }
}
