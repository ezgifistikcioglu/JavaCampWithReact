package com.homeworktwo.managements;

import com.homeworktwo.dataTypes.User;

public class InstructorManager extends UserManager{

    public void addHomework(User user){
        System.out.println("InstructorManager: " + user.getHomework());
    }
}
