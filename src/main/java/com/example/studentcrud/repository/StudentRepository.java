package com.example.studentcrud.repository;

import com.example.studentcrud.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.department = :department")
    List<Student> getStudentsByDepartment(
            @Param("department") String department);
}
