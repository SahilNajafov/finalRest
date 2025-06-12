package com.finalprojectrest.mapper;

import com.finalprojectrest.dto.RestBookDto;
import com.finalprojectrest.entity.Book;

import java.util.List;


public class BookMapper {
    public static List<RestBookDto> mapRestResponseToDto(List<Book> bookDto) {
        return bookDto.stream().map(book -> RestBookDto.builder()
                        .year(book.getYear())
                        .name(book.getName())
                        .image(book.getImage())
                        .author(book.getAuthor())
                        .category(book.getCategory().getName())
                        .language(book.getLanguage() == null ? null : book.getLanguage().getAzName())
                        .publishing(book.getPublishing())
                        .build())
                .toList();
    }
}
