package com.interfaces;

public class FileLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println("File is logged : " + message);
    }
}
