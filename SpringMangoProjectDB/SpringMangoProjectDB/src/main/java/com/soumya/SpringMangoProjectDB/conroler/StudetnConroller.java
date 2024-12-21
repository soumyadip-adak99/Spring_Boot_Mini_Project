package com.soumya.SpringMangoProjectDB.conroler;

import com.soumya.SpringMangoProjectDB.entity.Student;
import com.soumya.SpringMangoProjectDB.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:3000")
public class StudetnConroller {
    @Autowired
    private StudentService studentService;

    // Save student details
    @PostMapping(value = "/save")
    public String saveStudent(@RequestBody Student student) {
        studentService.saveOrUpdate(student);
        return "Student saved successfully!";
    }

    // Get all student details
    @GetMapping("/getAll")
    public List<Student> getAllStudents() {
        return studentService.getAll();
    }

    // Get student details by ID
    @GetMapping("/getData/{id}")
    public Student getStudent(@PathVariable(name = "id") String id) {
        return studentService.getStudentById(id);
    }

    // Update student details
    @PutMapping("/edit/{id}")
    public Student editStudent(@PathVariable(name = "id") String id, @RequestBody Student student) {
        student.setId(id);
        studentService.saveOrUpdate(student);
        return student;
    }

    // Delete student details
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable(name = "id") String id) {
        studentService.deleteStudent(id);
        return "Student deleted successfully!";
    }
}
