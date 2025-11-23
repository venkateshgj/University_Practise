package com.springboot.University.Service;

import com.springboot.University.DTO.StudentDTO;
import com.springboot.University.Entity.Student;

import java.util.List;

public interface StudentService {

    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(Long id);

    Student createStudent(StudentDTO studentDto);

    StudentDTO updateStudent(StudentDTO student, Long id);

    boolean deleteStudentById(Long id);
}
