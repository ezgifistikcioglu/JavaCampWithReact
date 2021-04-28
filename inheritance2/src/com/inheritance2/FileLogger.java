package com.inheritance2;

public class FileLogger extends Logger{
    //Method overriding
    @Override
    public void log(){
        System.out.println("Logged to File");
    }
}
