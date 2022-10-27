package com.ouyang;


import com.ouyang.netty.primary.MyServer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class TestApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class,args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        new MyServer().start();
    }
}
