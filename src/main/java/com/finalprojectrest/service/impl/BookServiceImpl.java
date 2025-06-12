package com.finalprojectrest.service.impl;

import com.finalprojectrest.dto.RestBookDto;
import com.finalprojectrest.repository.BookRepository;
import com.finalprojectrest.service.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.finalprojectrest.mapper.BookMapper.mapRestResponseToDto;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;

    @Override
    public List<RestBookDto> getAllBooksRest() {
        return mapRestResponseToDto(bookRepository.findAllByOrderByIdDesc());
    }
}
