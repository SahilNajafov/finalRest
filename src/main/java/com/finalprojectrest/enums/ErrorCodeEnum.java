package com.finalprojectrest.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCodeEnum {
    UNKNOWN_ERROR(1000, "unknown error!"),
    USER_NOT_FOUND(1001, "can not find user!"),
    VALIDATION_ERROR(1002, "is not valid!"),
    ALREADY_REGISTERED_ERROR(1003, "user already registered!"),
    TAKEN_EMAIL_ERROR(1004, "email already taken!"),
    BAD_CREDENTIALS_ERROR(1005, "change the credentials!"),
    ILLEGAL_ARGUMENT_ERROR(1012,"has been passed an illegal or inappropriate argument!");

    Integer code;
    String message;

}
