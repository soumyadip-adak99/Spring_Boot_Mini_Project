package com.soumya.SpringMangoProjectDB.services;

import com.soumya.SpringMangoProjectDB.entity.Student;
import com.soumya.SpringMangoProjectDB.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    // Save or update student
    public void saveOrUpdate(Student student) {
        studentRepo.save(student);
    }

    // Get a student by ID
    public Student getStudentById(String id) {
        Optional<Student> optionalStudent = studentRepo.findById(id);
        return optionalStudent.orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }

    // Get all students
    public List<Student> getAll() {
        return studentRepo.findAll();
    }

    // Delete a student by ID
    public void deleteStudent(String id) {
        if (studentRepo.existsById(id)) {
            studentRepo.deleteById(id);
        } else {
            throw new RuntimeException("Student not found with ID: " + id);
        }
    }

    // Update student details
    public Student updateStudent(String id, Student updatedStudent) {
        Student existingStudent = getStudentById(id); // Validates if the student exists
        if (updatedStudent.getName() != null) {
            existingStudent.setName(updatedStudent.getName());
        }
        if (updatedStudent.getAddress() != null) {
            existingStudent.setAddress(updatedStudent.getAddress());
        }
        if (updatedStudent.getPhone() != null) {
            existingStudent.setPhone(updatedStudent.getPhone());
        }
        return studentRepo.save(existingStudent);
    }

}
