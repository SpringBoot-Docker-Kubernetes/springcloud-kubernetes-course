package com.lagm.springcloud.msvc.courses.service;

import com.lagm.springcloud.msvc.courses.models.entity.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseService {
    List<Course> findAll();
    Optional<Course> findById(Long id);
    Course save(Course course);
    void deleteById(Long id);
}
