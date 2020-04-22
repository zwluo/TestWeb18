package com.example.TestWeb18;

public class PrintInfo
{

    public static void print(String type) throws InterruptedException {
        if("1".equals(type)) {
            System.out.println("sleep 10s");
            Thread.sleep(10000);
        }
        System.out.println("print: " + type);

    }

}
