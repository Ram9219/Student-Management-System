package com.lpu.studentmanagement.repository;

import com.lpu.studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByRollNo(int rollNo);
    List<Student> findByNameContainingIgnoreCase(String name);
    List<Student> findByCourseContainingIgnoreCase(String course);
    boolean existsByRollNo(int rollNo);
    void deleteByRollNo(int rollNo);

    @Query("SELECT COUNT(s), AVG(s.marks), MAX(s.marks), MIN(s.marks) FROM Student s")
    Object[] getStudentStatistics();
}