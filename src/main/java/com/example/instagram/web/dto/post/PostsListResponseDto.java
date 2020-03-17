package com.example.instagram.web.dto.post;

import java.util.List;
import lombok.Getter;

@Getter
public class PostsListResponseDto {

  List<PostListResponseDto> posts;

  public PostsListResponseDto(List<PostListResponseDto> entity) {
    this.posts = entity;
  }
}
