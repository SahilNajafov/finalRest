package com.finalprojectrest.service.impl;

import com.finalprojectrest.dto.request.PasswordRequest;
import com.finalprojectrest.entity.Student;
import com.finalprojectrest.repository.StudentRepository;
import com.finalprojectrest.service.StudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentServiceImpl implements StudentService {

    StudentRepository repository;
    PasswordEncoder encoder;

    @Override
    public String updatePassword(PasswordRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Student student = repository.findByEmail(email).orElseThrow(() -> new RuntimeException("Student not found"));
        if (!encoder.matches(request.getOldPassword(), student.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect");
        }
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("New passwords do not match");
        }

        student.setPassword(encoder.encode(request.getNewPassword()));
        repository.save(student);
        return "Updated password successfully";
    }
}
