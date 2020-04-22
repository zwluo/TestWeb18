package com.example.TestWeb18;

public class Test1 implements Runnable
{
    private String type;

    public Test1(String type) {
        this.type = type;
    }

    @Override
    public void run() {
        try {

            PrintInfo.print(String.valueOf(type));
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
