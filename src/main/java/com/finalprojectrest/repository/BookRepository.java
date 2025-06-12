package com.finalprojectrest.repository;

import com.finalprojectrest.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    @Query("SELECT b from Book b where b.isActive order by b.id desc")
    List<Book> findAllByOrderByIdDesc();

}