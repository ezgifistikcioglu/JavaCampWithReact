package com.kodlamaIoHw1;

import java.util.Arrays;

public class KodlamaIoManager {
    public void showInstructorNameAndCourses(Instructor instructor) {
        System.out.println("Instructor's name  : " + instructor.instructorName + "Courses : " + Arrays.toString(instructor.instructorCourses));
    }

    public void ShowCourseLanguageAndPrice(CourseDetails courseDetails) {
        System.out.println("Course language : " + courseDetails.courseLanguage + "Price : " + courseDetails.coursePrice);
    }

    public void ShowCategoryName(Category category) {
        System.out.println("Category name : " + category.CategoryName);
    }
}
