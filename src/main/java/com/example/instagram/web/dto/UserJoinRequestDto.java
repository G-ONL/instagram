package com.example.instagram.web.dto;

import com.example.instagram.common.PasswordEncoding;
import com.example.instagram.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserJoinRequestDto {

  private PasswordEncoding passwordEncoding = new PasswordEncoding();

  private String userName;
  private String password;

  public User toEntity() {
    return User.builder()
        .userName(userName)
        .password(passwordEncoding.encode(password))
        .build();
  }
}
