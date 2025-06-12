package com.finalprojectrest.controller;

import com.finalprojectrest.dto.RestEBookDto;
import com.finalprojectrest.service.EBookService;
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
public class RestEBookController {

    EBookService eBookService;

    @GetMapping("/ebooks")
    public ResponseEntity<List<RestEBookDto>> getEBooks() {
        return ResponseEntity.ok(eBookService.findAllRest());
    }
}
