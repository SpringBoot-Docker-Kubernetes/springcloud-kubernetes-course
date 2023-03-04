package com.lagm.springcloud.msvc.courses.service;

import com.lagm.springcloud.msvc.courses.clients.UserClientRest;
import com.lagm.springcloud.msvc.courses.models.User;
import com.lagm.springcloud.msvc.courses.models.entity.Course;
import com.lagm.springcloud.msvc.courses.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements ICourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserClientRest client;

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAll() {
        return (List<Course>)courseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    @Transactional
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Optional<User> assignUserToCourse(User user, Long courseId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> createAndAssignUserToCourse(User user, Long courseId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> unassignUserFromCourse(User user, Long courseId) {
        return Optional.empty();
    }
}
