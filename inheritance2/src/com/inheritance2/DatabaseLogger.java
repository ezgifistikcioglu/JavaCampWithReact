package com.inheritance2;

public class DatabaseLogger extends Mlogger{
    //Method overriding
    @Override
    public void log(){
        System.out.println("Logged to database");
    }
}
