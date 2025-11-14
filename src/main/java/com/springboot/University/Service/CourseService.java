package com.springboot.University.Service;

import com.springboot.University.DTO.CourseDTO;
import com.springboot.University.Entity.Course;

import java.util.List;

public interface CourseService {

    List<CourseDTO> getAllCourses();

    Course getCourseById(Long id);

    CourseDTO createCourse(CourseDTO courseDTO);

    Course updateCourse(Long id, Course course);

    boolean deleteCourseById(Long id);
}
