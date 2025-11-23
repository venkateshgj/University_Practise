package com.springboot.University.Service;

import com.springboot.University.DTO.StudentDTO;
import com.springboot.University.Entity.Student;
import com.springboot.University.Exceptions.ResourceNotFoundException;
import com.springboot.University.Util.Mapper;
import com.springboot.University.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class iStudentService implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private Mapper mapper;
    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> allStudents =  studentRepository.findAll();
        List<StudentDTO> studentDTOList = allStudents.stream().map(student -> mapper.studentToStudentDTO(student)).toList();
        return studentDTOList;
    }

    @Override
    public StudentDTO getStudentById(Long id) {

        Student student = studentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Student not found with ID : " + id)
        );

        return mapper.studentToStudentDTO(student);
    }

    @Override
    public Student createStudent(StudentDTO studentDto) {
        Student student = mapper.studentDtoToStudent(studentDto);
        Student savedStudent = studentRepository.save(student);
        return savedStudent;
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDto, Long id) {
        Optional<Student> existingStudentOptional = studentRepository.findById(id);
        Student existingStudent = existingStudentOptional.get();

        Student receivedStudentForUpdate = mapper.studentDtoToStudent(studentDto);
        existingStudent.setName(receivedStudentForUpdate.getName());
        existingStudent.setDepartment(receivedStudentForUpdate.getDepartment());
        existingStudent.setYear(receivedStudentForUpdate.getYear());
        existingStudent.setCourses(receivedStudentForUpdate.getCourses());

        Student updatedStudent = studentRepository.save(existingStudent);
        return mapper.studentToStudentDTO(updatedStudent);
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
