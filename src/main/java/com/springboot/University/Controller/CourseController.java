package com.springboot.University.Controller;

import com.springboot.University.DTO.CourseDTO;
import com.springboot.University.Entity.Course;
import com.springboot.University.Service.iCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/university/api/v1/course")
public class CourseController {

    @Autowired
    private iCourseService courseService;

    @GetMapping("/all")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseBasedOnId(@PathVariable Long id) {
        if(courseService.getCourseById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PostMapping("/new")
    public ResponseEntity<Course> createNewCourse(@RequestBody CourseDTO course) {
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Course> updateCourse(@RequestBody CourseDTO courseDto, @PathVariable Long id) {
        Course savedCourse = courseService.updateCourse(id, courseDto);
        if(savedCourse == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(savedCourse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        if(courseService.deleteCourseById(id) == true) {
            return ResponseEntity.ok("Deleted Course Record Successfully");
        };
        return ResponseEntity.ok("Course Record Not Found");
    }
}
