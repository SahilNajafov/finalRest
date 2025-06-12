package com.finalprojectrest.mapper;


import com.finalprojectrest.entity.EBook;
import com.finalprojectrest.dto.RestEBookDto;

import java.util.List;

public class EBookMapper {

    public static List<RestEBookDto> responseRestToDto(List<EBook> ebooks) {
        return ebooks.stream().map(ebook -> RestEBookDto.builder()
                .name(ebook.getName())
                .author(ebook.getAuthor())
                .language(ebook.getLanguage() == null ? null : ebook.getLanguage().getAzName())
                .category(ebook.getCategory().getName())
                .downloadUrl(ebook.getDownloadUrl())
                .year(ebook.getYear())
                .build()
        ).toList();
    }
}
