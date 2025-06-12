package com.finalprojectrest.repository;

import com.finalprojectrest.entity.EBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EBookRepository extends JpaRepository<EBook, Long> {
    List<EBook> findAllByOrderByIsbnDesc();
}
