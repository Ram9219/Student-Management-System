package com.lpu.studentmanagement.service;

import com.lpu.studentmanagement.entity.Student;
import com.lpu.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }

    public Student getStudentByRollNo(int rollNo) {
        return studentRepository.findByRollNo(rollNo);
    }

    public boolean addStudent(Student student) {
        try {
            if (studentRepository.existsByRollNo(student.getRollNo())) {
                return false;
            }
            studentRepository.save(student);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStudentMarks(int rollNo, int marks) {
        try {
            Student student = studentRepository.findByRollNo(rollNo);
            if (student != null) {
                student.setMarks(marks);
                studentRepository.save(student);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteStudent(int rollNo) {
        try {
            Student student = studentRepository.findByRollNo(rollNo);
            if (student != null) {
                studentRepository.delete(student);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Student> searchStudentsByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Student> searchStudentsByCourse(String course) {
        return studentRepository.findByCourseContainingIgnoreCase(course);
    }

    public boolean isRollNumberExists(int rollNo) {
        return studentRepository.existsByRollNo(rollNo);
    }

    public Object[] getStatistics() {
        return studentRepository.getStudentStatistics();
    }

    public Student getTopperStudent() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .max((s1, s2) -> Integer.compare(s1.getMarks(), s2.getMarks()))
                .orElse(null);
    }
}