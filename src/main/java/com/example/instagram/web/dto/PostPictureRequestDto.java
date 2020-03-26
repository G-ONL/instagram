package com.example.instagram.web.dto;

import com.example.instagram.domain.post.Post;
import com.example.instagram.domain.postPicture.PostPicture;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostPictureRequestDto {

  private String pictureUrl;

  public PostPicture toEntity() {
    return new PostPicture().builder()
        .pictureUrl(pictureUrl)
        .build();
  }
}
