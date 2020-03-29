package com.example.instagram.web.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class UserLoginRequestDto {

  @ApiModelProperty(notes = "아이디", example = "user1",position = 0)
  private String userName;
  @ApiModelProperty(notes = "비밀번호", example = "12341234",position = 1)
  private String password;
}
