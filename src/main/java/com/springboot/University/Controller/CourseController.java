package com.springboot.University.Controller;

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
    public ResponseEntity<List<Course>> getAllCourses() {
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
    public ResponseEntity<Course> createNewCourse(@RequestBody Course course) {
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course, @PathVariable Long id) {
        if(courseService.getCourseById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(courseService.updateCourse(id, course));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        if(courseService.deleteCourseById(id) == true) {
            return ResponseEntity.ok("Deleted Course Record Successfully");
        };
        return ResponseEntity.ok("Course Record Not Found");
    }
}
