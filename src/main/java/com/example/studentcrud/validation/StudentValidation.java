package com.example.studentcrud.validation;

import com.example.studentcrud.dto.StudentRequestDTO;
import com.example.studentcrud.exception.StudentException;

public class StudentValidation {

    public static void validate(StudentRequestDTO request) {

        if(request.getName()==null ||
                request.getName().trim().isEmpty()) {

            throw new StudentException(
                    "Name cannot be empty");
        }

        if(request.getDepartment()==null ||
                request.getDepartment().trim().isEmpty()) {

            throw new StudentException(
                    "Department cannot be empty");
        }

        if(request.getPassword()==null ||
                request.getPassword().length()<8) {

            throw new StudentException(
                    "Password must contain minimum 8 characters");
        }
    }
}

