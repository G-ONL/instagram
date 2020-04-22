package com.example.instagram.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Getter
public class UserAvatarSaveRequestDto {

  Long userId;
  private MultipartFile data;

  public void inputImg(MultipartFile file) {
    this.data = file;
  }

}
