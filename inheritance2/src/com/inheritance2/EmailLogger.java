package com.inheritance2;

public class EmailLogger extends Logger{
    //Method overriding
    @Override
    public void log(){
        System.out.println("Email Sent");
    }
}
