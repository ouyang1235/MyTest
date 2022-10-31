package com.ouyang.demo.rpc.impl;

import com.ouyang.demo.rpc.interfaces.AobingService;

public class AobingServiceImpl implements AobingService {
    @Override
    public String hello(String name) {
        return "hello!I am"+name;

    }
}
