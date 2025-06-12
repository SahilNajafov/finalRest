package com.finalprojectrest.service;

import com.finalprojectrest.dto.request.PasswordRequest;

public interface StudentService {
    String updatePassword(PasswordRequest request);
}
