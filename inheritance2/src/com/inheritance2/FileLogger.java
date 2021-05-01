package com.inheritance2;

public class FileLogger extends Mlogger{
    //Method overriding
    @Override
    public void log(){
        System.out.println("Logged to File");
    }
}
