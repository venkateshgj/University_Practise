package com.springboot.University.Service;

import com.springboot.University.DTO.CourseDTO;
import com.springboot.University.Entity.Course;
import com.springboot.University.Entity.Professor;
import com.springboot.University.Util.Mapper;
import com.springboot.University.Repository.CourseRepository;
import com.springboot.University.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class iCourseService implements CourseService{
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private Mapper mapper;
    @Override
    public List<CourseDTO> getAllCourses() {

        List<Course> allCourses = courseRepository.findAll();
        List<CourseDTO> courseDTOS = allCourses.stream().map( course -> mapper.courseToCourseDTO(course) ).toList();
        return courseDTOS;
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        return mapper.courseToCourseDTO(courseRepository.findById(id).orElse(null));
    }

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = mapper.courseDTOToCourse(courseDTO);
        return mapper.courseToCourseDTO(courseRepository.save(course));
    }


    @Override
    public CourseDTO updateCourse(Long id, CourseDTO courseDto) {
        Optional<Course> existingCourseOptional = courseRepository.findById(id);
        if(existingCourseOptional.isPresent()){
            Course existingCourse = existingCourseOptional.get();
            Course recievedCourse = mapper.courseDTOToCourse(courseDto);
            existingCourse.setTitle(recievedCourse.getTitle());
            existingCourse.setCredits(recievedCourse.getCredits());
            existingCourse.setDepartment(recievedCourse.getDepartment());
            existingCourse.setStudents(recievedCourse.getStudents());

            Optional<Professor> existingProfessorOptional = professorRepository.findById(courseDto.getProfessorId());
            if(existingProfessorOptional.isPresent()){
                existingCourse.setProfessor(existingProfessorOptional.get());
            }
            return mapper.courseToCourseDTO(courseRepository.save(existingCourse));
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
