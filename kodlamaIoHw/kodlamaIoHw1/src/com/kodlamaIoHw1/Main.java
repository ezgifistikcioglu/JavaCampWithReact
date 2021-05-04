package com.kodlamaIoHw1;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Category category = new Category(1, "Programlama");

        CourseDetails courseDetails1 = new CourseDetails(1, "Yazılım Geliştirici Yetiştirme Kampı Java", "Engin Demirog", "JAVA + REACT", 0, category);
        CourseDetails courseDetails2 = new CourseDetails(2, "Yazılım Geliştirici Yetiştirme Kampı C#", "Engin Demirog", "C# + ANGULAR", 0, category);
        CourseDetails courseDetails3 = new CourseDetails(3, "Programlamaya Giriş için Temel Kurs", "Engin Demirog", "All Programming languages", 0, category);

        String[] courseList = {courseDetails1.CourseName, courseDetails2.CourseName, courseDetails3.CourseName};

        Instructor instructor1 = new Instructor(1, courseDetails1.courseInstructor, courseList, courseDetails1);
        Instructor instructor2 = new Instructor(2, courseDetails1.courseInstructor, courseList, courseDetails2);
        Instructor instructor3 = new Instructor(3, courseDetails1.courseInstructor, courseList, courseDetails3);


        //initialize Instructor ArrayList
        Instructor[] instructors = {instructor1, instructor2, instructor3};
        //foreach loop
        for (Instructor instructor : instructors) {
            System.out.println(instructor.instructorName + " => " + Arrays.toString(instructor.instructorCourses));
        }

        //invoke KodlamaIoManager class.
        KodlamaIoManager kodlamaIoManager = new KodlamaIoManager();

        kodlamaIoManager.ShowCategoryName(category);

        kodlamaIoManager.showInstructorNameAndCourses(instructor1);
        kodlamaIoManager.showInstructorNameAndCourses(instructor2);
        kodlamaIoManager.showInstructorNameAndCourses(instructor3);

        kodlamaIoManager.ShowCourseLanguageAndPrice(courseDetails1);
        kodlamaIoManager.ShowCourseLanguageAndPrice(courseDetails2);
        kodlamaIoManager.ShowCourseLanguageAndPrice(courseDetails3);
    }
}
