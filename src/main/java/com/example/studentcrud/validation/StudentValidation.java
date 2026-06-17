package com.example.studentcrud.validation;

import com.example.studentcrud.dto.StudentRequestDTO;

public class StudentValidation {

    public static String validate(StudentRequestDTO request) {

        if(request.getName()==null ||
                request.getName().trim().isEmpty()) {
            return "Name cannot be empty";
        }

        if(request.getDepartment()==null ||
                request.getDepartment().trim().isEmpty()) {
            return "Department cannot be empty";
        }

        if(request.getPassword()==null ||
                request.getPassword().length()<8) {
            return "Password must contain minimum 8 characters";
        }

        return "VALID";
    }
    }

