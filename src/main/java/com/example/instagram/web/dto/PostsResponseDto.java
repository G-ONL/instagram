package com.example.instagram.web.dto;

import com.example.instagram.domain.posts.Posts;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class PostsResponseDto {

  private Long id;
  private String caption;
  private String likeCount;
  private LocalDateTime createdDate;
  private LocalDateTime modifiedDate;

  public PostsResponseDto(Posts entity) {
    this.id = entity.getId();
    this.caption = entity.getCaption();
    this.likeCount = entity.getLikeCount();
    this.createdDate = entity.getCreatedDate();
    this.modifiedDate = entity.getModifiedDate();
  }

}
