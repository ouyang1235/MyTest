package com.ouyang.demo.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class Producer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("firstProducer");

        producer.setNamesrvAddr("123.56.115.93:9876");
        producer.setSendMsgTimeout(60000);

        producer.start();

        Message msg = new Message("firstProducer", "TagA", "第一条消息".getBytes());
        SendResult sendResult = producer.send(msg);
        System.out.printf("%s%n",sendResult);

        //close
        producer.shutdown();
    }

}
