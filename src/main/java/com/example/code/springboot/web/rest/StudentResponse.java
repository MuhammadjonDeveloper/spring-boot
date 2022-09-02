package com.example.code.springboot.web.rest;

import com.example.code.springboot.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentResponse {
    /*  @GetMapping("/students")
      public String hello() {
          return "Hello world";
      }*/

    @GetMapping("/students")
    public ResponseEntity getAll() {
        Student student = new Student(1L, "Sobir", "Sobirov", "ivat");
        Student student1 = new Student(2L, "Jafar", "Sobirov", "ivat-1");
        Student student2 = new Student(3L, "Naim", "Sobirov", "ivat-2");
        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student1);
        students.add(student2);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        Student student = new Student(id, "Sobir", "Sobirov", "ivat");
        return ResponseEntity.ok(student);
    }

    @PostMapping("/students")
    public ResponseEntity create(@RequestBody Student student) {
        return ResponseEntity.ok(student);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity update(@PathVariable Long id,
                                 @RequestBody Student studentNew) {
        Student student = new Student(1L, "Sobir", "Sobirov", "ivat");
        student.setId(id);
        student.setName(studentNew.getName());
        student.setLastName(studentNew.getLastName());
        return ResponseEntity.ok(student);
    }
}
