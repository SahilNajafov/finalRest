package com.finalprojectrest.dto.response;


import com.finalprojectrest.enums.ErrorCodeEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private Integer code;
    private String message;

    public static ErrorResponse of(ErrorCodeEnum errorCodeEnum) {
        return ErrorResponse.builder()
                .code(errorCodeEnum.getCode())
                .message(errorCodeEnum.getMessage())
                .build();
    }

    public static ErrorResponse of(ErrorCodeEnum errorCodeEnum, String message) {
        return ErrorResponse.builder()
                .code(errorCodeEnum.getCode())
                .message(String.format("%s %s ",
                        message,
                        errorCodeEnum.getMessage()))
                .build();
    }
}
