package com.finalprojectrest.controller;

import com.finalprojectrest.dto.RestBookDto;
import com.finalprojectrest.service.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RestBookController {
    BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<RestBookDto>> getBooks() {
        return ResponseEntity.ok(bookService.getAllBooksRest());
    }
}
