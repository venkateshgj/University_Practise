package com.springboot.University.Service;

import com.springboot.University.Entity.Student;
import com.springboot.University.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class iStudentService implements StudentService{

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        Optional<Student> existingStudentOptional =  studentRepository.findById(id);
        if(existingStudentOptional.isPresent()){
            return existingStudentOptional.get();
        }
        return null;
    }

    @Override
    public Student createStudent(Student student) {
        Student savedStudent = studentRepository.save(student);
        return savedStudent;
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        Optional<Student> existingStudentOptional = studentRepository.findById(id);
        Student existingStudent = existingStudentOptional.get();
        existingStudent.setName(student.getName());
        existingStudent.setDepartment(student.getDepartment());
        existingStudent.setYear(student.getYear());

        Student updatedStudent = studentRepository.save(existingStudent);
        return updatedStudent;
    }

    @Override
    public boolean deleteStudentById(Long id) {
        if(studentRepository.existsById(id)){
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
