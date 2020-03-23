package com.example.instagram.web.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginResponseDto {

  private Long userId;

  public UserLoginResponseDto(Long id) {
    this.userId = id;
  }
}
