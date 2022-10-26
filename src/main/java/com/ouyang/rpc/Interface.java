package com.ouyang.rpc;

import com.ouyang.rpc.framework.AobingRpcFramework;
import com.ouyang.rpc.interfaces.AobingService;

public class Interface {
    public static void main(String[] args) throws Exception {
        //服务调用者只需要设置依赖
        AobingService serviced = AobingRpcFramework.refer(AobingService.class, "127.0.0.1", 2333);
        serviced.hello("ouyang");
    }
}
