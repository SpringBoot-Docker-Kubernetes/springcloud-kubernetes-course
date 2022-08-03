package com.lagm.springcloud.msvc.courses.repository;

import com.lagm.springcloud.msvc.courses.models.entity.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
