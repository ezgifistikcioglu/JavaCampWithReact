package com.homeworktwo.dataTypes;

public class Instructor extends User {
    private int instructorId;
    private String firstName;
    private String lastName;
    private String about;
    private String certificate;

    public Instructor() {
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public Instructor(int userId, String emailAddress, String password, String contact, String address, String[] courses, String homework, int instructorId, String firstName, String lastName, String about, String certificate) {
        super(userId, emailAddress, password, contact, address, courses, homework);
        this.instructorId = instructorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.about = about;
        this.certificate = certificate;

    }
}
