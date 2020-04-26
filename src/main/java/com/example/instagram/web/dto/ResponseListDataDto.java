package com.example.instagram.web.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseListDataDto<T> {

  private int status;
  private String message;
  private List<T> data;

  public ResponseListDataDto(int status) {
    if (200 == status) {
      this.message = "Success";
    } else {
      this.message = "Fail";
    }
    this.status = status;
  }

  public ResponseListDataDto(int status, T data) {
    if (200 == status) {
      this.message = "Success";
    } else {
      this.message = "Fail";
    }
    this.status = status;
    this.data = List.of(data);
  }

  public ResponseListDataDto(int status, List<T> data) {
    if (200 == status) {
      this.message = "Success";
    } else {
      this.message = "Fail";
    }
    this.status = status;
    this.data = data;
  }

  public ResponseListDataDto(int status, String message, List<T> data) {
    this.status = status;
    this.message = message;
    this.data = data;
  }


}
