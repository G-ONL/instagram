package com.example.instagram.web;

import com.example.instagram.common.CommonConstant;
import com.example.instagram.service.UserService;
import com.example.instagram.web.dto.user.UserLoginRequestDto;
import com.example.instagram.web.dto.user.UserJoinRequestDto;
import com.example.instagram.web.dto.user.UserSaveResponseDto;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

  private final UserService userService;

  @PostMapping("user/join")
  public ResponseEntity<UserSaveResponseDto> join(@RequestBody UserJoinRequestDto requestDto) {
    return ResponseEntity.status(HttpStatus.OK).body(new UserSaveResponseDto(userService.join(requestDto)));
  }

  @PostMapping("user/login")
  public ResponseEntity login(@RequestBody UserLoginRequestDto requestDto,
      HttpServletResponse response) {
    response.setHeader(CommonConstant.AUTHORIZATION, userService.login(requestDto));
    return ResponseEntity.ok(requestDto.getUserName());
  }
}
