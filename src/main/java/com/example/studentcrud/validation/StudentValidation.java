package com.example.studentcrud.validation;

import com.example.studentcrud.dto.StudentRequestDTO;

public class StudentValidation {

    public static void validate(StudentRequestDTO request) {

        if(request.getName()==null ||
                request.getName().trim().isEmpty()) {

            throw new RuntimeException(
                    "Name cannot be empty");
        }

        if(request.getDepartment()==null ||
                request.getDepartment().trim().isEmpty()) {

            throw new RuntimeException(
                    "Department cannot be empty");
        }

        if(request.getPassword()==null ||
                request.getPassword().length()<8) {

            throw new RuntimeException(
                    "Password must contain minimum 8 characters");
        }
    }
}

