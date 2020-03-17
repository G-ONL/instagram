package com.example.instagram.web.dto.comment;


import com.example.instagram.domain.comment.Comment;
import lombok.Getter;

@Getter
public class CommentRequestDto {

  private Long userId;
  private Long postId;
  private String comment;

  public Comment toEntity() {
    return Comment.builder()
        .comment(comment)
        .build();
  }
}
