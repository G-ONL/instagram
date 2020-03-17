package com.example.instagram.web.dto.comment;

import com.example.instagram.domain.comment.Comment;
import com.example.instagram.web.dto.post.PostResponseDto;
import lombok.Getter;

@Getter
public class CommentResponseDto {

  private Long id;
  private String comment;

  public CommentResponseDto(Comment comment) {
    this.id = comment.getId();
    this.comment = comment.getComment();
  }
}
