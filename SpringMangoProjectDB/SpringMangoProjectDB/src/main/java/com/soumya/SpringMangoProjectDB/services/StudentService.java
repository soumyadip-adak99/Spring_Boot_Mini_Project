package com.soumya.SpringMangoProjectDB.services;

import com.soumya.SpringMangoProjectDB.entity.Student;
import com.soumya.SpringMangoProjectDB.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public void saveOrUpdate(Student student) {
        studentRepo.save(student);
    }

    //update method
    public Student updateById(String id) {
        return studentRepo.findById(id).orElse(null);
    }

    public List<Student> getAll() {
        return studentRepo.findAll();
    }

    public void deleteStudent(String id) {
        studentRepo.deleteById(id);
    }

    public Student getStudentById(String id) {
        return studentRepo.findById(id).get();
    }

}
