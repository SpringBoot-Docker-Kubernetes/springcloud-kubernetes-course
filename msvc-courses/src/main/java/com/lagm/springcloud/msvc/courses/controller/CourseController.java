package com.lagm.springcloud.msvc.courses.controller;

import com.lagm.springcloud.msvc.courses.models.User;
import com.lagm.springcloud.msvc.courses.models.entity.Course;
import com.lagm.springcloud.msvc.courses.service.ICourseService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

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
    public ResponseEntity<?> save(@Valid @RequestBody Course course, BindingResult result) {
        if (result.hasErrors()) {
            return validInput(result);
        }

        Course courseSaved = courseService.save(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Course course, BindingResult result, @PathVariable("id") Long id) {
        if (result.hasErrors()) {
            return validInput(result);
        }

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

    private static ResponseEntity<Map<String, String>> validInput(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }

    @PutMapping("/assign-user/{courseId}")
    public ResponseEntity<?> assignUserToCourse(@RequestBody User user, @PathVariable("courseId") Long courseId) {
        Optional<User> optionalUser;

        try {
            optionalUser = courseService.assignUserToCourse(user, courseId);

            if (optionalUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(optionalUser.get());
            }

            return ResponseEntity.notFound().build();

        } catch (FeignException e) {
            return ResponseEntity.status(e.status()).body(
                    Collections.singletonMap("mensaje", "No existe el usuario por id o error en la comunicacion: " + e.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("mensaje", e.getMessage()));
        }
    }

    @PostMapping("/create-assign-user/{courseId}")
    public ResponseEntity<?> createAndAssignUserToCourse(@RequestBody User user, @PathVariable("courseId") Long courseId) {
        Optional<User> optionalUser;

        try {
            optionalUser = courseService.createAndAssignUserToCourse(user, courseId);

            if (optionalUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(optionalUser.get());
            }

            return ResponseEntity.notFound().build();

        } catch (FeignException e) {
            return ResponseEntity.status(e.status()).body(
                    Collections.singletonMap("mensaje", "Error al crear el usuario o error en la comunicación: " + e.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("mensaje", e.getMessage()));
        }
    }

    @DeleteMapping("/unassign-user/{courseId}")
    public ResponseEntity<?> unassignUserFromCourse(@RequestBody User user, @PathVariable("courseId") Long courseId) {
        Optional<User> optionalUser;

        try {
            optionalUser = courseService.unassignUserFromCourse(user, courseId);

            if (optionalUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(optionalUser.get());
            }

            return ResponseEntity.notFound().build();

        } catch (FeignException e) {
            return ResponseEntity.status(e.status()).body(
                    Collections.singletonMap("mensaje", "No existe el usuario por el id o error en la comunicación: " + e.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("mensaje", e.getMessage()));
        }
    }
}
