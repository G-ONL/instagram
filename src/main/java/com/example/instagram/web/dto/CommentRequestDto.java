package com.example.instagram.web.dto;


import lombok.Getter;

@Getter
public class CommentRequestDto {

  private Long userId;
  private Long postId;
  private String comment;

}
