package com.example.instagram.service;

import com.example.instagram.domain.user.User;
import com.example.instagram.domain.user.UserRepository;
import com.example.instagram.web.dto.UserJoinRequestDto;
import com.example.instagram.web.dto.UserLoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private PasswordEncoder passwordEncoding = new BCryptPasswordEncoder();

  private final UserRepository userRepository;
  private final JwtService jwtService;

  public Long join(UserJoinRequestDto requestDto) {
    return userRepository.save(new UserJoinRequestDto(requestDto.getUserName(),
        passwordEncoding.encode(requestDto.getPassword())).toEntity()).getId();
  }

  public String login(UserLoginRequestDto requestDto) {
    User user = userRepository.findByUserName(requestDto.getUserName());
    if (user == null) {
      throw new IllegalArgumentException("아이디를 확인해주세요");
    }
    if (!passwordEncoding.matches(requestDto.getPassword(), user.getPassword())) {
      throw new IllegalArgumentException("비밀번호를 확인해주세요");
    }
    return jwtService.create(user.getId());
  }

}
