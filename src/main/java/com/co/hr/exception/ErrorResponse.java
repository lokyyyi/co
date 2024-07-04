package com.co.hr.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private String code;

    public ErrorResponse(ErrorCode errorCode) {
        this.setMessage(errorCode.getMessage());
        this.setCode(String.valueOf(errorCode.getCode()));
    }
}
