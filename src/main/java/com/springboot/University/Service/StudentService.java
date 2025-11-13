package com.springboot.University.Service;

import com.springboot.University.Entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();

    Student getStudentById(Long id);

    Student createStudent(Student student);

    Student updateStudent(Student student, Long id);

    boolean deleteStudentById(Long id);
}
