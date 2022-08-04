package com.lagm.springcloud.msvc.courses.models.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseUser> listCourseUser;

    public Course() {
        this.listCourseUser = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CourseUser> getCourseUser() {
        return listCourseUser;
    }

    public void setCourseUser(List<CourseUser> courseUser) {
        this.listCourseUser = courseUser;
    }

    public void addCourseUser(CourseUser courseUser) {
        this.listCourseUser.add(courseUser);
    }

    public void removeCourseUser(CourseUser courseUser) {
        this.listCourseUser.remove(courseUser);
    }
}
