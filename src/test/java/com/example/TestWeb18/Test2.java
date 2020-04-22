package com.example.TestWeb18;

public class Test2
{

    public static void main(String[] args) {
        Test1 test0 = new Test1("0");
        new Thread(test0).start();

        Test1 test1 = new Test1("1");
        Thread thread = new Thread(test1);
        thread.start();

        Test1 test2 = new Test1("2");
        new Thread(test2).start();

    }

}
