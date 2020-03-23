package com.example.instagram.web.dto.user;

import com.example.instagram.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserJoinRequestDto {


  private String userName;
  private String password;

  public UserJoinRequestDto(String userName, String password){
    this.userName = userName;
    this.password = password;
  }

  public User toEntity() {
    return User.builder()
        .userName(userName)
        .password(password)
        .build();
  }

}
