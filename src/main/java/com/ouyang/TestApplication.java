package com.ouyang;


import com.ouyang.netty.primary.MyServer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController("/v1")
public class TestApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class,args);
    }

    @Autowired
    private RocketMQTemplate template;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        new MyServer().start();
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello world!";
    }
}
