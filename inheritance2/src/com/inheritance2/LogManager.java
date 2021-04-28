package com.inheritance2;

public class LogManager {
    // Bad code example
    /*
     * 1 => Database
     * 2 => File
     * 3 => Email
     */
    public void log(int logType) {
        //Birbirinin alternatifi olan kodlar if ile yönetilemez.
        if (logType == 1) {
            System.out.println("Logged to database");
        }else if (logType == 2) {
            System.out.println("Logged to File");
        }else {
            System.out.println("Email Sent");
        }

    }
}
