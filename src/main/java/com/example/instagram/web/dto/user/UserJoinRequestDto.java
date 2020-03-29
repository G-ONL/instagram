package com.example.instagram.web.dto.user;

import com.example.instagram.domain.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserJoinRequestDto {

  @ApiModelProperty(notes = "아이디", example = "user1",position = 0)
  private String userName;
  @ApiModelProperty(notes = "비밀번호", example = "12341234",position = 1)
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
