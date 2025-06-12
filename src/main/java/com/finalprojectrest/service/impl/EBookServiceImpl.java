package com.finalprojectrest.service.impl;

import com.finalprojectrest.dto.RestEBookDto;
import com.finalprojectrest.repository.EBookRepository;
import com.finalprojectrest.service.EBookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.finalprojectrest.mapper.EBookMapper.responseRestToDto;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EBookServiceImpl implements EBookService {

    EBookRepository repository;

    @Override
    public List<RestEBookDto> findAllRest() {
        return responseRestToDto(repository.findAllByOrderByIsbnDesc());
    }

}
