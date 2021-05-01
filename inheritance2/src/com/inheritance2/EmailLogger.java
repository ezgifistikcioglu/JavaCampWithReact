package com.inheritance2;

public class EmailLogger extends Mlogger{
    //Method overriding
    @Override
    public void log(){
        System.out.println("Email Sent");
    }
}
