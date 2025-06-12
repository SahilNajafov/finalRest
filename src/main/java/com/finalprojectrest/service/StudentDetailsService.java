package com.finalprojectrest.service;

import com.finalprojectrest.entity.Student;
import com.finalprojectrest.repository.StudentRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class StudentDetailsService implements UserDetailsService {

    private final StudentRepository studentRepository;

    public StudentDetailsService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Student not found"));

        return new org.springframework.security.core.userdetails.User(
                student.getEmail(),
                student.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_STUDENT"))
        );
    }
}

