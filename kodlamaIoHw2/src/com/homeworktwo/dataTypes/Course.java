package com.homeworktwo.dataTypes;

//Not added yet.
public class Course {
    private int id;
    private int coursePrice;
    private int category;
    private String courseName;

    public Course(int id, int coursePrice, int category, String courseName) {
        this.id = id;
        this.coursePrice = coursePrice;
        this.category = category;
        this.courseName = courseName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(int coursePrice) {
        this.coursePrice = coursePrice;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
