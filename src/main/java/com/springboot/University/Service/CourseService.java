package com.springboot.University.Service;

import com.springboot.University.DTO.CourseDTO;
import com.springboot.University.Entity.Course;

import java.util.List;

public interface CourseService {

    List<CourseDTO> getAllCourses();

    CourseDTO getCourseById(Long id);

    CourseDTO createCourse(CourseDTO courseDTO);

    CourseDTO updateCourse(Long id, CourseDTO course);

    boolean deleteCourseById(Long id);
}
