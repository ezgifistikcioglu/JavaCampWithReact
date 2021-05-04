package com.homeworktwo.managements;

import com.homeworktwo.dataTypes.Student;
import com.homeworktwo.dataTypes.User;


public class StudentManager extends UserManager{

    public void addHomework(User user){
        System.out.println("StudentManager: " + user.getHomework());
    }

    public void doAttendance(Student student){
        System.out.println("Attendance Status: "+ student.isAttendance());
    }

    public void showProgress(Student student){
        System.out.println(student.getProgress());
    }
}
