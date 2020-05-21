package com.example.TestWeb18;

import com.example.TestWeb18.util.HttpClientUtil;
import org.apache.http.HttpEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.*;

public class Test13
{

    @Test
    public void testPost() throws IOException {
        String url = "http://122.97.12.74:8066/Middleware/requestHandle/handlePost.do";
        Map<String, String> map = new HashMap<String, String>();
        // 测试获取WOS SKU库存分布信息
        map.put("apiKey", "ceshib2bdXNvc2ovU3luYy9hZGRUYXNbmFsQXB");
        map.put("postValue", "{\"id\":\"5\",\"AuthKey\":\"f64f3ed3-58b6-4998-9c89-6f5f55b2151d\",\"tempOrders\":{\"shipCity\":\"shanghai\",\"shipName\":\"datou\"}}");
        HttpEntity entity = HttpClientUtil.doPost(url, map, "UTF-8",null);
        if(entity != null) {
            String result = new BasicResponseHandler().handleEntity(entity);
            System.out.println("result: " + result);
        }
    }

    @Test
    public void testTrim() {
        // 空格 32
        // a 97
        // A 65
        char c = 'A';
        System.out.println((int)c);

        String a = "";
        System.out.println("a: [" + a.trim() + "]");

    }

    public String trim(String str) {
        char[] c = str.toCharArray();
        int lt = str.length();
        int st = 0;
        while((st < lt) && (c[st] <= ' ')) {
            st++;
        }
        while((st < lt) && (c[lt-1] <= ' ')) {
            lt--;
        }
        return (st>0||lt<str.length())?str.substring(st,lt):str;
    }

    @Test
    public void testRedisService() {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());

    }

    @Test
    public void testRedisString() {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //设置 redis 字符串数据
        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: " + jedis.get("runoobkey"));

    }

    @Test
    public void testRedisList() {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //存储数据到列表中
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");

        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0, 2);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("列表项为: " + list.get(i));
        }

    }

    @Test
    public void testRedisKey() {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");

        // 获取数据并输出
        Set<String> keys = jedis.keys("*");
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key);
        }

    }

}
