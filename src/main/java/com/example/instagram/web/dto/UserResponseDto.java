package com.example.instagram.web.dto;

import com.example.instagram.domain.user.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

  private String userName;
  private String location;
  private String avatarUrl;

  public UserResponseDto(User user) {
    this.userName = user.getUserName();
    this.location = user.getLocation();
    this.avatarUrl = user.getAvatarUrl();
  }
}
