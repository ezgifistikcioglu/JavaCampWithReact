package com.homeworktwo;

import com.homeworktwo.managements.UserManager;
import com.homeworktwo.managements.InstructorManager;
import com.homeworktwo.managements.StudentManager;

import com.homeworktwo.dataTypes.User;
import com.homeworktwo.dataTypes.Student;
import com.homeworktwo.dataTypes.Instructor;

public class Main {

    public static void main(String[] args) {
        String message = "UserManager message sent";
        String address = "Unknown";
        String homework = "This week's assignments have been uploaded ";
        String homeworkStudent = "I completed this week's assignments ";

        User user = new User();
        user.setEmailAddress("somebody@gmail.com");
        user.setAddress(address);
        user.setContact(message);

        UserManager userManager = new UserManager();
        userManager.editProfile(user);
        userManager.addAddress(user);
        userManager.sendMessage(user);

        System.out.println("-----------------------------");

        Instructor instructor = new Instructor();
        instructor.setHomework(homework);

        InstructorManager instructorManager = new InstructorManager();
        instructorManager.addHomework(instructor);

        System.out.println("-----------------------------");

        Student student = new Student();
        student.setHomework(homeworkStudent);
        student.setAttendance(true);

        StudentManager studentManager = new StudentManager();
        studentManager.addHomework(student);
        studentManager.doAttendance(student);
    }
}
