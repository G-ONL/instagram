package com.example.instagram.exception;

import com.example.instagram.web.dto.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestControllerExceptionAdvice {

  @ExceptionHandler(JwtException.class)
  public ResponseEntity<ExceptionResponseDto> notFoundToken() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(
        ExceptionResponseDto.builder().status(HttpStatus.UNAUTHORIZED.value())
            .message("토큰이 존재하지 않습니다.")
            .build());
  }

  @ExceptionHandler(PostException.class)
  public ResponseEntity<ExceptionResponseDto> notFoundPost() {
    return ResponseEntity.badRequest().body(
        ExceptionResponseDto.builder().status(HttpStatus.BAD_REQUEST.value())
            .message("포스트가 존재하지 않습니다.")
            .build());
  }

  @ExceptionHandler(UserException.class)
  public ResponseEntity<ExceptionResponseDto> notFoundUser(Exception e) {
    return ResponseEntity.badRequest().body(
        ExceptionResponseDto.builder().status(HttpStatus.BAD_REQUEST.value())
            .message(e.getMessage())
            .build());
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ExceptionResponseDto> duplicateUserName(Exception e) {
    return ResponseEntity.badRequest().body(
        ExceptionResponseDto.builder().status(HttpStatus.BAD_REQUEST.value())
            .message(e.getMessage())
            .build());
  }
}
