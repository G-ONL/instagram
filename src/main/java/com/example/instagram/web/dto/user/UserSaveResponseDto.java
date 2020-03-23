package com.example.instagram.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveResponseDto {

  private Long userId;

  public UserSaveResponseDto(Long id){
    this.userId = id;
  }
}
