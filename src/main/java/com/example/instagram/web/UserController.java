package com.example.instagram.web;

import com.example.instagram.common.CommonConstant;
import com.example.instagram.service.JwtService;
import com.example.instagram.service.UserService;
import com.example.instagram.web.dto.ResponseMessageDto;
import com.example.instagram.web.dto.user.UserAvatarSaveRequestDto;
import com.example.instagram.web.dto.user.UserJoinRequestDto;
import com.example.instagram.web.dto.user.UserLoginRequestDto;
import com.example.instagram.web.dto.user.UserLoginResponseDto;
import io.swagger.annotations.Api;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(description = "회원가입/로그인", tags = "User")
@RequiredArgsConstructor
@RestController
public class UserController {

  private final UserService userService;
  private final JwtService jwtService;

  @PostMapping("user/join")
  public ResponseEntity<ResponseMessageDto> join(@RequestBody UserJoinRequestDto requestDto) {
    userService.join(requestDto);
    return ResponseEntity.ok(new ResponseMessageDto(HttpStatus.OK.value()));
  }

  @PostMapping("user/login")
  public ResponseEntity<ResponseMessageDto> login(@RequestBody UserLoginRequestDto requestDto,
      HttpServletResponse response) {
    UserLoginResponseDto userLoginResponseDto = userService.login(requestDto);
    response.setHeader(CommonConstant.AUTHORIZATION,
        jwtService.create(userLoginResponseDto.getUserId()));
    return ResponseEntity.ok(new ResponseMessageDto(HttpStatus.OK.value()));
  }

  @PostMapping("user/avatar")
  public ResponseEntity<ResponseMessageDto> avatarUpload(
      @RequestParam("file") MultipartFile[] files,
      HttpServletRequest request) throws IOException {
    Long userId = Long.valueOf(String.valueOf(request.getAttribute(CommonConstant.USER_ID)));
    UserAvatarSaveRequestDto saveRequestDto = new UserAvatarSaveRequestDto(userId, files[0]);

    userService.saveAvatar(saveRequestDto);
    return ResponseEntity.ok(new ResponseMessageDto(HttpStatus.OK.value()));
  }
}
