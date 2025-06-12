package com.finalprojectrest.service;

import com.finalprojectrest.dto.RestEBookDto;
import java.util.List;

public interface EBookService {
    List<RestEBookDto> findAllRest();
}
