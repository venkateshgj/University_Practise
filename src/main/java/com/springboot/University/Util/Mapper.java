package com.springboot.University.Util;

import com.springboot.University.DTO.CourseDTO;
import com.springboot.University.DTO.ProfessorDTO;
import com.springboot.University.DTO.StudentDTO;
import com.springboot.University.Entity.Course;
import com.springboot.University.Entity.Professor;
import com.springboot.University.Entity.Student;
import com.springboot.University.Exceptions.InvalidRequestException;
import com.springboot.University.Exceptions.ResourceNotFoundException;
import com.springboot.University.Repository.CourseRepository;
import com.springboot.University.Repository.ProfessorRepository;
import com.springboot.University.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapper {
    
    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

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

                for (Course c : courses) {
                    c.setProfessor(professor);
                }
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

            if(course.getStudents() != null){
                List<Long> studentIds = course.getStudents().stream().map(x -> x.getId()).toList();
                courseDTO.setStudentIds(studentIds);
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

            if(courseDTO.getProfessorId() == null){
                throw new InvalidRequestException("Professor Id is required");
            }

            Professor professorReturned = professorRepository.findById(courseDTO.getProfessorId()).orElseThrow(
                    () -> new ResourceNotFoundException("Professor not found with ID : " + courseDTO.getProfessorId())
            );
            course.setProfessor( professorReturned);

            if(courseDTO.getStudentIds() != null){
                List<Student> students = studentRepository.findAllById(courseDTO.getStudentIds());
                course.setStudents(students);
            }
            else {
                course.setStudents(null);
                throw new InvalidRequestException("Students list is required");
            }


            return course;
        }
        return null;
    }

    public StudentDTO studentToStudentDTO(Student student){
        if(student != null){
            StudentDTO studentDto = new StudentDTO();
            studentDto.setId(student.getId());
            studentDto.setName(student.getName());
            studentDto.setDepartment(studentDto.getDepartment());
            studentDto.setYear(student.getYear());
            if(student.getCourses() != null){
                List<Long> courseIds = student.getCourses().stream().map(course -> course.getId()).toList();
                studentDto.setCourseIds(courseIds);
            }
            return studentDto;
        }
        return null;
    }

    public Student studentDtoToStudent(StudentDTO studentDTO){
        if (studentDTO != null) {
            Student student = new Student();
            student.setId(studentDTO.getId());
            student.setName(studentDTO.getName());
            student.setDepartment(studentDTO.getDepartment());
            student.setYear(studentDTO.getYear());

            if(studentDTO.getCourseIds() != null){
                List<Course> course = courseRepository.findAllById(studentDTO.getCourseIds());
                student.setCourses(course);
            }

            return student;
        }
        return null;
    }

}
