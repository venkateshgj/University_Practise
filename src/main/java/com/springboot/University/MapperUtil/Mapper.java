package com.springboot.University.MapperUtil;

import com.springboot.University.DTO.CourseDTO;
import com.springboot.University.DTO.ProfessorDTO;
import com.springboot.University.Entity.Course;
import com.springboot.University.Entity.Professor;
import com.springboot.University.Repository.CourseRepository;
import com.springboot.University.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapper {
    
    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private CourseRepository courseRepository;

    public ProfessorDTO professorToProfessorDTO(Professor professor){
        if(professor != null){
            ProfessorDTO professorDTO = new ProfessorDTO();
            professorDTO.setId(professor.getId());
            professorDTO.setName(professor.getName());
            professorDTO.setDepartment(professor.getDepartment());

            if (professor.getCourses() != null) {
                List<Long> courseIds = professor.getCourses().stream().map(course -> course.getId()).toList();
                professorDTO.setCourseIds(courseIds);
            }
            return professorDTO;
        }
        return null;
    }
    public Professor professorDTOToProfessor(ProfessorDTO professorDTO){
        if(professorDTO != null){
            Professor professor = new Professor();
            professor.setId(professorDTO.getId());
            professor.setName(professorDTO.getName());
            professor.setDepartment(professorDTO.getDepartment());

            if(professorDTO.getCourseIds() != null){
                List<Course> courses = courseRepository.findAllById(professorDTO.getCourseIds());
                professor.setCourses(courses);
            }
            return professor;
        }
        return null;
    }

    public CourseDTO courseToCourseDTO(Course course){
        if(course != null) {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(course.getId());
            courseDTO.setTitle(course.getTitle());
            courseDTO.setCredits(course.getCredits());
            courseDTO.setDepartment(course.getDepartment());

            if (course.getProfessor() != null) {
                courseDTO.setProfessorId(course.getProfessor().getId());
            }
            return courseDTO;
        }
        return null;
    }

    public Course courseDTOToCourse(CourseDTO courseDTO){
        if(courseDTO != null) {
            Course course = new Course();
            course.setId(courseDTO.getId());
            course.setTitle(courseDTO.getTitle());
            course.setCredits(courseDTO.getCredits());
            course.setDepartment(courseDTO.getDepartment());
            
            Professor professorReturned = professorRepository.findById(courseDTO.getProfessorId()).orElse(null);
            course.setProfessor( professorReturned);
            return course;
        }
        return null;
    }

}
