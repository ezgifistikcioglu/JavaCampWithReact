package com.kodlamaIoHw1;

public class CourseDetails {
    int id;
    String CourseName;
    String courseInstructor;
    String courseLanguage;
    int coursePrice;
    Category category;

    public CourseDetails(int id, String courseName, String courseInstructor, String courseLanguage, int coursePrice, Category category) {
        this.id = id;
        CourseName = courseName;
        this.courseInstructor = courseInstructor;
        this.courseLanguage = courseLanguage;
        this.coursePrice = coursePrice;
        this.category = category;
    }
}
