package com.example.instagram.web.dto;

import com.example.instagram.common.PasswordEncoding;
import com.example.instagram.domain.user.User;
import lombok.Getter;

@Getter
public class UserRegisterRequestDto {

  private PasswordEncoding passwordEncoding = new PasswordEncoding();
  private String userName;
  private String password;

  public User toEntity() {
    return User.builder()
        .userName(userName)
        .password(passwordEncryption(password))
        .build();
  }

  private String passwordEncryption(String password) {
    return passwordEncoding.encode(password);
  }
}
