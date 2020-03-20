package com.example.instagram.web;

import com.example.instagram.service.UserService;
import com.example.instagram.web.dto.UserLoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

  private final UserService userService;

  @PostMapping("/login")
  public String login(@RequestBody UserLoginRequestDto requestDto) {
    userService.login(requestDto);
    return null;
  }
}
