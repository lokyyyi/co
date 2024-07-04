package com.co.hr.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    BAD_REQUEST(401, "BAD REQUEST"),
    UNAUTHORIZED(401, "UNAUTHORIZED"),
    NOT_FOUND(404, "RESULT NOT FOND"),
    INTER_SERVER_ERROR(405, "BACK-END SERVER ERROR"),
    DATA_EXISTS(406, "DATA EXISTS"),
    TOO_MANY_RESULT(407, "TOO MANY RESULT"),
    ACCOUNT_DUPLICATION(400, "STAFF-ACCOUNT DUPLICATION");

    private int code;
    private String message;
}