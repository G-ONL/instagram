package com.example.instagram.web.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseMessageDto {

  private int status;
  private String message;

  public ResponseMessageDto(int status) {
    if (200 == status) {
      this.message = "Success";
    } else {
      this.message = "Fail";
    }
    this.status = status;
  }

  public ResponseMessageDto(int status, String message) {
    this.status = status;
    this.message = message;
  }


}
