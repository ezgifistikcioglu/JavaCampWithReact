package com.homeworktwo.managements;

import com.homeworktwo.dataTypes.User;

public class UserManager {

    public void addHomework(User user){
        System.out.println("UserManager: " + user.getHomework());
    }

    public void editProfile(User user){
        System.out.println("Email Changed to " + user.getEmailAddress());
    }

    public void addAddress(User user){
        System.out.println("Address Changed to " + user.getAddress());

    }

    public void sendMessage(User user){
        System.out.println(user.getContact());
    }
}
