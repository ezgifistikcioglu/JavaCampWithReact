package com.homeworktwo.dataTypes;

public class Student extends User {
    private boolean attendance = false;
    private int progress;

    public Student() {
    }

    public Student(int id, String fullName, String emailAddress, String password, String contact, String address, String[] courses, String homework, boolean attendance, int progress) {
        super(id, fullName, emailAddress, password, contact, address, courses, homework);
        this.attendance = attendance;
        this.progress = progress;
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
}
