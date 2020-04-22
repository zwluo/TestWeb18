package com.example.TestWeb18.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Productor
{
    private static final String QUEUE_NAME = "q_test_02";

    public static void main(String[] args) throws Exception {
        // 获取到连接以及MQ通道
        Connection connection = RabbitMQConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 消息内容
        String message = "Hello World3!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        // 关闭通道和连接
        channel.close();
        connection.close();

    }

}
