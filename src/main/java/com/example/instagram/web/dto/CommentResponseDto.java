package com.example.instagram.web.dto;

import com.example.instagram.domain.comment.Comment;
import com.example.instagram.domain.post.Post;
import lombok.Getter;

@Getter
public class CommentResponseDto {

  private Long id;
  private String comment;
  private PostResponseDto post;

  public CommentResponseDto(Comment comment) {
    this.id = comment.getId();
    this.comment = comment.getComment();
    this.post = new PostResponseDto(comment.getPost());
  }
}
