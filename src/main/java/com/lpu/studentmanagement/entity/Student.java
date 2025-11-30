package com.lpu.studentmanagement.entity;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "roll_no", unique = true, nullable = false)
    private int rollNo;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 50)
    private String course;

    @Column(nullable = false)
    private int marks;

    public Student() {}

    public Student(int rollNo, String name, int age, String email, String course, int marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.email = email;
        this.course = course;
        this.marks = marks;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getRollNo() { return rollNo; }
    public void setRollNo(int rollNo) { this.rollNo = rollNo; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public int getMarks() { return marks; }
    public void setMarks(int marks) { this.marks = marks; }

    public String getGrade() {
        if (marks >= 90) return "A+";
        if (marks >= 80) return "A";
        if (marks >= 70) return "B+";
        if (marks >= 60) return "B";
        if (marks >= 50) return "C";
        if (marks >= 40) return "D";
        return "F";
    }

    public String getStatus() {
        return marks >= 40 ? "PASS" : "FAIL";
    }

    public String getGradeColor() {
        if (marks >= 90) return "success";
        if (marks >= 75) return "primary";
        if (marks >= 50) return "warning";
        return "danger";
    }
}