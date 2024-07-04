package com.co.hr.exception;

import com.co.hr.common.dto.ResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StaffExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(StaffExceptionHandler.class);

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResultDto<String>> handleBadRequestException(BadRequestException ex) {
        //log.info(String.valueOf(ex.toString()));
        //log.info(String.valueOf(ex.getErrorCode().toString()));
        //log.info(String.valueOf(ex.getErrorCode().getCode()));
        ResultDto<String> resultDto = new ResultDto<String>(ex.getErrorCode().getCode(), ex.getMessage());
        //log.info(String.valueOf(resultDto.getStatusCode()));
        log.error("BadRequestException: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(resultDto, HttpStatus.valueOf(ex.getErrorCode().getCode()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResultDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("HTTP message not readable exception occurred: {}", ex.getMessage());
        ResultDto resultDto = new ResultDto(HttpStatus.BAD_REQUEST.value(), "잘못된 요청입니다(JSON Parsing Error).");
        return new ResponseEntity<>(resultDto, HttpStatus.BAD_REQUEST);
    }
}
