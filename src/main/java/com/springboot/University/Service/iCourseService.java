package com.springboot.University.Service;

import com.springboot.University.DTO.CourseDTO;
import com.springboot.University.Entity.Course;
import com.springboot.University.MapperUtil.Mapper;
import com.springboot.University.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class iCourseService implements CourseService{
    @Autowired
    private CourseRepository courseRepository;
    @Override
    public List<CourseDTO> getAllCourses() {

        List<Course> allCourses = courseRepository.findAll();
        List<CourseDTO> courseDTOS = allCourses.stream().map( course -> Mapper.courseToCourseDTO(course) ).toList();
        return courseDTOS;
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

//    @Override
//    public Course createCourse(Course course) {
//        return courseRepository.save(course);
//    }

    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = Mapper.courseDTOToCourse(courseDTO);
        return null;
    }


    @Override
    public Course updateCourse(Long id, Course course) {
        Optional<Course> existingCourseOptional = courseRepository.findById(id);
        if(existingCourseOptional.isPresent()){
            Course existingCourse = existingCourseOptional.get();
            existingCourse.setTitle(course.getTitle());
            existingCourse.setCredits(course.getCredits());
            existingCourse.setDepartment(course.getDepartment());
            return courseRepository.save(existingCourse);
        }
        return null;
    }

    @Override
    public boolean deleteCourseById(Long id) {
        if(courseRepository.existsById(id)){
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
