package com.ouyang;


import com.ouyang.demo.SpringCOntext;
import com.ouyang.demo.TestController;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class TestApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class,args);
    }

    @Autowired
    private RocketMQTemplate template;

    @Autowired
    private SpringCOntext springCOntext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        new MyServer().start();
        System.out.println("server start");
        TestController bean = springCOntext.getBean(TestController.class);
        System.out.println(bean.hello());
    }

//    @GetMapping("/hello")
//    public String hello(){
//        return "hello world!";
//    }
}
