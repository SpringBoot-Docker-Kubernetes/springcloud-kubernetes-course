package com.lagm.springcloud.msvc.courses.models.entity;

import com.lagm.springcloud.msvc.courses.models.User;

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
    @JoinColumn(name = "course_id")
    private List<CourseUser> listCourseUsers;

    @Transient
    private List<User> listUsers;

    public Course() {
        this.listCourseUsers = new ArrayList<>();
        this.listUsers = new ArrayList<>();
    }

    public void addCourseUser(CourseUser courseUser) {
        this.listCourseUsers.add(courseUser);
    }

    public void removeCourseUser(CourseUser courseUser) {
        this.listCourseUsers.remove(courseUser);
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

    public List<CourseUser> getListCourseUsers() {
        return listCourseUsers;
    }

    public void setListCourseUsers(List<CourseUser> listCourseUsers) {
        this.listCourseUsers = listCourseUsers;
    }

    public List<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<User> listUsers) {
        this.listUsers = listUsers;
    }
}
