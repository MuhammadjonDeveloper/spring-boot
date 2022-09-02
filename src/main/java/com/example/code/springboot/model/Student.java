package com.example.code.springboot.model;

public class Student {
    private long id;
    private String name;
    private String lastName;
    private String course;

    public Student(long id, String name, String lastName, String course) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.course = course;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCourse() {
        return course;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
