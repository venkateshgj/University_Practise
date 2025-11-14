package com.springboot.University.MapperUtil;

import com.springboot.University.DTO.CourseDTO;
import com.springboot.University.DTO.ProfessorDTO;
import com.springboot.University.Entity.Course;
import com.springboot.University.Entity.Professor;
import com.springboot.University.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Mapper {
    
    @Autowired
    private static ProfessorRepository professorRepository;

    public static ProfessorDTO entityToProfessorDTO(Professor professor){
        if(professor != null){
            ProfessorDTO professorDTO = new ProfessorDTO();
            professorDTO.setId(professor.getId());
            professorDTO.setName(professor.getName());
            professorDTO.setDepartment(professor.getDepartment());

            if (professor.getCourses() != null) {
                professorDTO.setCourseIds(
                        professor.getCourses().stream().map(course -> course.getId()).toList());
            }
            return professorDTO;
        }
        return null;
    }
    public static Professor professorDTOToEntity(ProfessorDTO professorDTO){
        return null;
    }

    public static CourseDTO courseToCourseDTO(Course course){
        if(course != null) {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(course.getId());
            courseDTO.setTitle(course.getTitle());
            courseDTO.setCredits(course.getCredits());
            courseDTO.setDepartment(course.getDepartment());

            if (course.getProfessor() != null) {
                courseDTO.setProfessorsId(course.getProfessor().getId());
            }
            return courseDTO;
        }
        return null;
    }

    public static Course courseDTOToCourse(CourseDTO courseDTO){
        if(courseDTO != null) {
            Course course = new Course();
            course.setId(courseDTO.getId());
            course.setTitle(courseDTO.getTitle());
            course.setCredits(courseDTO.getCredits());
            course.setDepartment(courseDTO.getDepartment());
            
            course.setProfessor(
                    professorRepository.findById(courseDTO.getProfessorsId()).orElse(null)
            );
            return course;
        }
        return null;
    }

}
