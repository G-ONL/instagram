package com.example.instagram.web;

import com.example.instagram.common.CommonConstant;
import com.example.instagram.service.JwtService;
import com.example.instagram.service.UserService;
import com.example.instagram.web.dto.ResponseDto;
import com.example.instagram.web.dto.user.UserLoginRequestDto;
import com.example.instagram.web.dto.user.UserJoinRequestDto;
import com.example.instagram.web.dto.user.UserLoginResponseDto;
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
  private final JwtService jwtService;

  @PostMapping("user/join")
  public ResponseEntity<ResponseDto> join(@RequestBody UserJoinRequestDto requestDto) {
    return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), userService.join(requestDto)));
  }

  @PostMapping("user/login")
  public ResponseEntity<ResponseDto> login(@RequestBody UserLoginRequestDto requestDto,
      HttpServletResponse response) {
    UserLoginResponseDto userLoginResponseDto = userService.login(requestDto);
    response.setHeader(CommonConstant.AUTHORIZATION,
        jwtService.create(userLoginResponseDto.getUserId()));
    return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), userLoginResponseDto));
  }
}
