package com.example.instagram.web.dto;

import com.example.instagram.domain.postPicture.PostPicture;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class PostPictureResponseDto {

  private Long id;
  private String pictureUrl;

  public PostPictureResponseDto(PostPicture entity) {
    this.id = entity.getId();
    this.pictureUrl = entity.getPictureUrl();
  }
}
