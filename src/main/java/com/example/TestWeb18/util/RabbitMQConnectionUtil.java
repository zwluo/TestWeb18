package com.example.TestWeb18.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQConnectionUtil
{
    public static Connection getConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("localhost");
        //端口
        factory.setPort(5672);
        //设置账户信息,用户名,密码
        factory.setVirtualHost("testhost");
        factory.setUsername("admin");
        factory.setPassword("luozhiwei123");
        //获取连接
        Connection connection = factory.newConnection();
        return connection;

    }

}
