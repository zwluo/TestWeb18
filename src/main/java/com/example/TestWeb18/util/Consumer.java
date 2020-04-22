package com.example.TestWeb18.util;

import com.rabbitmq.client.*;

import javax.validation.groups.Default;
import java.io.IOException;

public class Consumer
{
    private static final String QUEUE_NAME = "q_test_02";

    public static void main(String[] args) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = RabbitMQConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel);
        consumer.getConsumerTag();

        // 监听队列
        //channel.basicConsume(QUEUE_NAME, true, consumer);
        //channel.getDefaultConsumer();

        // 获取消息
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });


        channel.basicConsume(QUEUE_NAME, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                    Envelope envelope,
                    AMQP.BasicProperties properties,
                    byte[] body) throws IOException{
                String key = envelope.getRoutingKey();
                String message = new String(body);
                long deliveryTag = envelope.getDeliveryTag();
                if (key.startsWith("io")) { // only consumer io warning messages
                    //consume message
                    System.out.println("consumed: " + message);
                    channel.basicAck(deliveryTag,false);

                } else { //reject other messages and requeue them
                    System.out.println("rejected: " + message);
                    channel.basicReject(deliveryTag,true);
                }
            }
        });


    }

}
