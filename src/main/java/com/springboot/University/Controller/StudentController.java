package com.springboot.University.Controller;

import com.springboot.University.Entity.Student;
import com.springboot.University.Service.StudentService;
import com.springboot.University.Service.iStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/university/api/v1/students")
public class StudentController {
    @Autowired
    private iStudentService studentService;
    @GetMapping("/all")
    public ResponseEntity<List<Student>> listAllStudent(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentBasedOnId(@PathVariable Long id){
        if(studentService.getStudentById(id) == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping("/new")
    public ResponseEntity<Student> createNewStudentRecord(@RequestBody Student student){
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudentRecord(@RequestBody Student student, @PathVariable Long id){
        if(studentService.getStudentById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentService.updateStudent(student, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudentRecord(@PathVariable Long id){
        if(studentService.deleteStudentById(id) == true){
            return ResponseEntity.ok("Deleted Student Record Successfully");
        };
        return ResponseEntity.ok("Student Record Not Found");
    }
}
