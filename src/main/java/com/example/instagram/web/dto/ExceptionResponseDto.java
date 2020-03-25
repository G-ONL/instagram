package com.example.instagram.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ExceptionResponseDto {

  private int status;
  private String message;

  public ExceptionResponseDto(int status) {
    this.status = status;
  }

  @Builder
  public ExceptionResponseDto(int status, String message) {
    this.status = status;
    this.message = message;
  }
}
