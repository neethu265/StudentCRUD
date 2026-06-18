package com.example.studentcrud.service;

import com.example.studentcrud.dto.ErrorResponseDTO;
import com.example.studentcrud.dto.StudentRequestDTO;
import com.example.studentcrud.dto.StudentResponseDTO;
import com.example.studentcrud.entity.Student;
import com.example.studentcrud.exception.StudentException;
import com.example.studentcrud.repository.StudentRepository;
import com.example.studentcrud.validation.StudentValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository repository;

    // CREATE
    public Object save(StudentRequestDTO request) {

        StudentValidation.validate(request);

        Student student = new Student();

        student.setName(request.getName());
        student.setDepartment(request.getDepartment());
        student.setPassword(request.getPassword());

        Student savedStudent = repository.save(student);

        StudentResponseDTO response = new StudentResponseDTO();

        response.setId(savedStudent.getId());
        response.setName(savedStudent.getName());
        response.setDepartment(savedStudent.getDepartment());

        return response;
    }

    // READ ALL
    public List<StudentResponseDTO> getAll() {

        List<Student> students = repository.findAll();

        List<StudentResponseDTO> dtoList = new ArrayList<>();

        for (Student student : students) {

            StudentResponseDTO dto = new StudentResponseDTO();

            dto.setId(student.getId());
            dto.setName(student.getName());
            dto.setDepartment(student.getDepartment());

            dtoList.add(dto);
        }

        return dtoList;
    }

    // READ BY ID

    @Cacheable(value = "student", key = "#id")
    public StudentResponseDTO getById(Long id) {

        System.out.println("Fetching from Database");

        Student student = repository.findById(id)
                .orElseThrow(() ->
                        new StudentException("Student Not Found"));

        StudentResponseDTO dto = new StudentResponseDTO();

        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setDepartment(student.getDepartment());

        return dto;
    }

    // UPDATE
    @CachePut(value = "student", key = "#id")
    public StudentResponseDTO update(Long id,
                                     StudentRequestDTO request) {

        StudentValidation.validate(request);

        Student student = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student Not Found"));

        student.setName(request.getName());
        student.setDepartment(request.getDepartment());
        student.setPassword(request.getPassword());

        Student updatedStudent = repository.save(student);

        StudentResponseDTO dto = new StudentResponseDTO();

        dto.setId(updatedStudent.getId());
        dto.setName(updatedStudent.getName());
        dto.setDepartment(updatedStudent.getDepartment());

        return dto;
    }

    public List<StudentResponseDTO> getByDepartment(
            String department) {

        List<Student> students =
                repository.getStudentsByDepartment(department);

        List<StudentResponseDTO> dtoList =
                new ArrayList<>();

        for(Student student : students) {

            StudentResponseDTO dto =
                    new StudentResponseDTO();

            dto.setId(student.getId());
            dto.setName(student.getName());
            dto.setDepartment(student.getDepartment());

            dtoList.add(dto);
        }

        return dtoList;
    }
    // DELETE
    @CacheEvict(value = "student", key = "#id")
    public void delete(Long id) {

        Student student = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student Not Found"));

        repository.delete(student);
    }
}