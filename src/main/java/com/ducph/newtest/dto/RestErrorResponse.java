package com.ducph.newtest.dto;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class RestErrorResponse {

    private int statusCode;
    private String reason;
    private String timestamp;

    public RestErrorResponse(HttpStatus status, String message) {
        this.statusCode = status.value();
        this.reason = StringUtils.isEmpty(message) ? status.getReasonPhrase() : message;
        this.timestamp = LocalDateTime.now().toString();
    }
}
