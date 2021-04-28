package com.inheritance2;

public class DatabaseLogger extends Logger{
    //Method overriding
    @Override
    public void log(){
        System.out.println("Logged to database");
    }
}
