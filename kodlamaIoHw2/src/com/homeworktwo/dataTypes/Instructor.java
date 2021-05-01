package com.homeworktwo.dataTypes;

public class Instructor extends User {
    private String about;
    private String certificate;

    public Instructor() {
    }

    public Instructor(int id, String fullName, String emailAddress, String password, String contact, String address, String[] courses, String homework, String about, String certificate) {
        super(id, fullName, emailAddress, password, contact, address, courses, homework);
        this.about = about;
        this.certificate = certificate;
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
}
