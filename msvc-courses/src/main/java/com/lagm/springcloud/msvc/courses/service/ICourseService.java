package com.lagm.springcloud.msvc.courses.service;

import com.lagm.springcloud.msvc.courses.models.User;
import com.lagm.springcloud.msvc.courses.models.entity.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseService {
    List<Course> findAll();
    Optional<Course> findById(Long id);
    Course save(Course course);
    void deleteById(Long id);
    Optional<User> assignUserToCourse(User user, Long courseId);
    Optional<User> createAndAssignUserToCourse(User user, Long courseId);
    Optional<User> unassignUserFromCourse(User user, Long courseId);
}
