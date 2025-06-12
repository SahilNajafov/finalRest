package com.finalprojectrest.repository;

import com.finalprojectrest.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);

    Student findByName(String name);

    Optional<Student> findByStudentFIN(String studentFIN);
}
