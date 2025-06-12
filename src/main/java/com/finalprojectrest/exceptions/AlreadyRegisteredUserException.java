package com.finalprojectrest.exceptions;

import com.finalprojectrest.enums.ErrorCodeEnum;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AlreadyRegisteredUserException extends RuntimeException {
    Integer code;
    String message;

    public AlreadyRegisteredUserException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMessage());
        this.code = errorCodeEnum.getCode();
        this.message = errorCodeEnum.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
