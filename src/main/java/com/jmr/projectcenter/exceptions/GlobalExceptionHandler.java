package com.jmr.projectcenter.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ErrorBody> error(SecurityException e){
        log.warn("发生security异常");
        return new ResponseEntity<>(
                ErrorBody.builder()
                        .desc("Token非法，用户不允许访问")
                        .code(HttpStatus.UNAUTHORIZED.value())
                        .build(), HttpStatus.UNAUTHORIZED);
    }
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class ErrorBody {
    private String desc;
    private int code;
}
