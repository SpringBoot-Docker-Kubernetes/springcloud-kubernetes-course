package com.lagm.springcloud.msvc.courses.controller;

import com.lagm.springcloud.msvc.courses.models.entity.Course;
import com.lagm.springcloud.msvc.courses.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(courseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Course> optCourse = courseService.findById(id);
        if (optCourse.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // return ResponseEntity.ok().body(optCourse.get());
        return ResponseEntity.ok(optCourse.get());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Course course) {
        Course courseSaved = courseService.save(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Course course, @PathVariable("id") Long id) {
        Optional<Course> optCourse = courseService.findById(id);
        if (optCourse.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Course courseDB = optCourse.get();
        courseDB.setName(course.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(courseDB));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Course> optCourse = courseService.findById(id);
        if (optCourse.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
        // return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
