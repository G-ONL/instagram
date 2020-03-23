package com.example.instagram.service;

import com.example.instagram.domain.user.User;
import com.example.instagram.domain.user.UserRepository;
import com.example.instagram.web.dto.user.UserJoinRequestDto;
import com.example.instagram.web.dto.user.UserLoginRequestDto;
import com.example.instagram.web.dto.user.UserLoginResponseDto;
import com.example.instagram.web.dto.user.UserSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private PasswordEncoder passwordEncoding = new BCryptPasswordEncoder();

  private final UserRepository userRepository;

  public UserSaveResponseDto join(UserJoinRequestDto requestDto) {
    User user = new UserJoinRequestDto(requestDto.getUserName(),
        passwordEncoding.encode(requestDto.getPassword())).toEntity();
    return new UserSaveResponseDto(userRepository.save(user).getId());
  }

  public UserLoginResponseDto login(UserLoginRequestDto requestDto) {
    User user = userRepository.findByUserName(requestDto.getUserName());
    if (user == null) {
      throw new IllegalArgumentException("아이디를 확인해주세요");
    }
    if (!passwordEncoding.matches(requestDto.getPassword(), user.getPassword())) {
      throw new IllegalArgumentException("비밀번호를 확인해주세요");
    }
    return new UserLoginResponseDto(user.getId());
  }

}
