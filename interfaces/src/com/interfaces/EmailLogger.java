package com.interfaces;

public class EmailLogger implements Logger{
    @Override
    public void log(String message) {
        System.out.println("Email sent : " + message);
    }
}
