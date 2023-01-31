package com.melita.erp.orderservice.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
public class ErrorDetails {
    private int status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;
    private String error;
    private String message;
    private List<Object> errorDetails;

    public ErrorDetails(Date timestamp, int status, String error, String message, String errorDetail) {
        this.timestamp=timestamp;
        this.status=status;
        this.error=error;
        this.message=message;
        this.errorDetails= Arrays.asList(errorDetail);

    }
    public ErrorDetails(Date timestamp, int status, String error, String message, List<Object> errorDetail) {
        this.timestamp=timestamp;
        this.status=status;
        this.message=message;
        this.errorDetails=errorDetail;
    }
}
