package com.example.studentcrud.controller;

import com.example.studentcrud.dto.StudentRequestDTO;
import com.example.studentcrud.dto.StudentResponseDTO;
import com.example.studentcrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService service;

    // CREATE
    @PostMapping
    public Object addStudent(@RequestBody StudentRequestDTO request) {
        return service.save(request);
    }

    // READ ALL
    @GetMapping
    public List<StudentResponseDTO> getStudents() {

        return service.getAll();
    }

    // READ ONE
    @GetMapping("/{id}")
    public StudentResponseDTO getStudentById(
            @PathVariable Long id) {

        return service.getById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public StudentResponseDTO updateStudent(
            @PathVariable Long id,
            @RequestBody StudentRequestDTO request) {

        return service.update(id, request);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteStudent(
            @PathVariable Long id) {

        service.delete(id);

        return "Student Deleted Successfully";
    }
}