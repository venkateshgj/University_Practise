package com.springboot.University.Service;

import com.springboot.University.Entity.Course;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourses();

    Course getCourseById(Long id);

    Course createCourse(Course course);

    Course updateCourse(Long id, Course course);

    boolean deleteCourseById(Long id);
}
