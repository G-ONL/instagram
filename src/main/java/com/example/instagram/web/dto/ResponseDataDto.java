package com.example.instagram.web.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseDataDto<T> {

  private int status;
  private String message;
  private List<T> data;

  public ResponseDataDto(int status) {
    if (200 == status) {
      this.message = "Success";
    } else {
      this.message = "Fail";
    }
    this.status = status;
  }

  public ResponseDataDto(int status, T data) {
    if (200 == status) {
      this.message = "Success";
    } else {
      this.message = "Fail";
    }
    this.status = status;
    this.data = List.of(data);
  }

  public ResponseDataDto(int status, List<T> data) {
    if (200 == status) {
      this.message = "Success";
    } else {
      this.message = "Fail";
    }
    this.status = status;
    this.data = data;
  }

  public ResponseDataDto(int status, String message, List<T> data) {
    this.status = status;
    this.message = message;
    this.data = data;
  }


}
