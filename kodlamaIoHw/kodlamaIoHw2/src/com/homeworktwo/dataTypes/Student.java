package com.homeworktwo.dataTypes;

public class Student extends User {
    private int studentId;
    private String firstName;
    private String lastName;
    private boolean attendance = false;
    private int progress;

    public Student() {
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAttendance() {
        return attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public Student(int userId, String emailAddress, String password, String contact, String address, String[] courses, String homework, int studentId, String firstName, String lastName, boolean attendance, int progress) {
        super(userId, emailAddress, password, contact, address, courses, homework);
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.attendance = attendance;
        this.progress = progress;

    }
}
