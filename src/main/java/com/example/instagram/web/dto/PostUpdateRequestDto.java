package com.example.instagram.web.dto;

import com.example.instagram.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDto {

  private String caption;
  private String likeCount;

  @Builder
  public PostUpdateRequestDto(String caption, String likeCount) {
    this.caption = caption;
    this.likeCount = likeCount;
  }

  public Post toEntity() {
    return Post.builder()
        .caption(caption)
        .likeCount(likeCount)
        .build();
  }

}
