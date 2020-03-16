package com.example.instagram.web.dto;

import com.example.instagram.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {

  private String caption;
  private String likeCount;

  @Builder
  public PostsUpdateRequestDto(String caption, String likeCount) {
    this.caption = caption;
    this.likeCount = likeCount;
  }

  public Posts toEntity() {
    return Posts.builder()
        .caption(caption)
        .likeCount(likeCount)
        .build();
  }

}
