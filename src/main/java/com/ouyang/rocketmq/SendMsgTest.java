package com.ouyang.rocketmq;

import com.ouyang.MyTestApplication;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MyTestApplication.class)
@RunWith(SpringRunner.class)
public class SendMsgTest {

    @Autowired
    private RocketMQTemplate template;

    @Test
    public void send() throws InterruptedException {
        template.convertAndSend("myTopic","测试消息啦！");
        Thread.sleep(60000);
    }


}
