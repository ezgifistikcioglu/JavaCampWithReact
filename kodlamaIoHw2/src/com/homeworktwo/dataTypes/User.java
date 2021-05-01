package com.homeworktwo.dataTypes;

public class User {
    private int userId;
    private String emailAddress;
    private String password;
    private String contact;
    private String address;
    private String homework;
    private String[] courses;

    public User() {
    }

    public User(int userId, String emailAddress, String password, String contact, String address, String[] courses, String homework) {
        this.userId = userId;
        this.emailAddress = emailAddress;
        this.password = password;
        this.contact = contact;
        this.address = address;
        this.homework = homework;
        this.courses = courses;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    public String[] getCourses() {
        return courses;
    }

    public void setCourses(String[] courses) {
        this.courses = courses;
    }
}
