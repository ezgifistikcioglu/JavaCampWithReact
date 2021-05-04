package com.kodlamaIoHw1;

public class Instructor {
    int id;
    String instructorName;
    String[] instructorCourses;
    CourseDetails courseDetails;

    public Instructor(int id, String instructorName, String[] instructorCourses, CourseDetails courseDetails) {
        this.id = id;
        this.instructorName = instructorName;
        this.instructorCourses = instructorCourses;
        this.courseDetails = courseDetails;
    }
}
