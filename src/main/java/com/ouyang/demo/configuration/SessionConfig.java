package com.ouyang.demo.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400 * 30)
public class SessionConfig {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
    }
}
