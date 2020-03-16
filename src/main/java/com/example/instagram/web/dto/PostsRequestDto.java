package com.example.instagram.web.dto;

import com.example.instagram.domain.posts.Posts;
import lombok.Builder;

public class PostsRequestDto {

  private String caption;
  private String likeCount;

  @Builder
  public PostsRequestDto(String caption, String likeCount) {
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
