package com.example.instagram.web.dto;

import com.example.instagram.domain.post.Post;
import com.example.instagram.domain.postPicture.PostPicture;
import lombok.Getter;

@Getter
public class PostPictureRequestDto {

  private String pictureUrl;

  public PostPicture toEntity() {
    return new PostPicture().builder()
        .pictureUrl(pictureUrl)
        .build();
  }
}
