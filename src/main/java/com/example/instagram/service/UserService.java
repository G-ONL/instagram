package com.example.instagram.service;

import com.example.instagram.common.S3Uploader;
import com.example.instagram.domain.user.User;
import com.example.instagram.domain.user.UserRepository;
import com.example.instagram.exception.UserException;
import com.example.instagram.web.dto.user.UserAvatarSaveRequestDto;
import com.example.instagram.web.dto.user.UserJoinRequestDto;
import com.example.instagram.web.dto.user.UserLoginRequestDto;
import com.example.instagram.web.dto.user.UserLoginResponseDto;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

  private PasswordEncoder passwordEncoding = new BCryptPasswordEncoder();

  private final UserRepository userRepository;
  private final S3Uploader s3Uploader;

  @Transactional
  public Long join(UserJoinRequestDto requestDto) {
    User user = new UserJoinRequestDto(requestDto.getUserName(),
        passwordEncoding.encode(requestDto.getPassword())).toEntity();
    User checkUser = userRepository.findByUserName(requestDto.getUserName());
    if (checkUser != null) {
      throw new UsernameNotFoundException("이미 등록된 아이디 입니다.");
    }
    return userRepository.save(user).getId();
  }

  @Transactional
  public UserLoginResponseDto login(UserLoginRequestDto requestDto) {
    User user = userRepository.findByUserName(requestDto.getUserName());
    if (user == null) {
      throw new UserException("아이디를 확인해주세요");
    }
    if (!passwordEncoding.matches(requestDto.getPassword(), user.getPassword())) {
      throw new UserException("비밀번호를 확인해주세요");
    }
    return new UserLoginResponseDto(user.getId());
  }

  @Transactional
  public void saveAvatar(UserAvatarSaveRequestDto saveRequestDto) throws IOException {
    User user = userRepository.findById(saveRequestDto.getUserId()).orElseThrow(
        () -> new UserException("존재하지 않는 유저 입니다.")
    );
    String pictureUrl = s3Uploader.upload(saveRequestDto.getData(), "static");
    user.addAvatar(pictureUrl);
  }
}
