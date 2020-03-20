package com.example.instagram.service;

import com.example.instagram.common.PasswordEncoding;
import com.example.instagram.domain.user.User;
import com.example.instagram.domain.user.UserRepository;
import com.example.instagram.web.dto.UserLoginRequestDto;
import com.example.instagram.web.dto.UserRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private PasswordEncoding passwordEncoding = new PasswordEncoding();
  private final UserRepository userRepository;

  public Long join(UserRegisterRequestDto requestDto) {
    return userRepository.save(requestDto.toEntity()).getId();
  }

  public String login(UserLoginRequestDto requestDto) {
    User user =  userRepository.findByUserNameAndPassword(requestDto.getUserName(), passwordEncoding.encode(requestDto.getPassword()));

    return null;

  }
}
