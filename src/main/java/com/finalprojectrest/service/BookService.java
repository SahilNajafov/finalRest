package com.finalprojectrest.service;

import com.finalprojectrest.dto.RestBookDto;
import java.util.List;

public interface BookService {

    List<RestBookDto> getAllBooksRest();
}
