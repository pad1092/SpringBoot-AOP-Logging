package com.pad.loggingwithaop.payload;

import lombok.*;
import org.springframework.http.HttpStatus;
@Builder
@Getter
@Setter
@ToString
public class ResponseDTO {
    @Builder.Default
    private boolean isSuccess = true;
    @Builder.Default
    private HttpStatus status = HttpStatus.OK;
    private Object detail;
}
