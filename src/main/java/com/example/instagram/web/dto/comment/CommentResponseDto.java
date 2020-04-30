package com.example.instagram.web.dto.comment;

import com.example.instagram.domain.comment.Comment;
import com.example.instagram.domain.user.User;
import com.example.instagram.web.dto.post.PostResponseDto;
import lombok.Getter;

@Getter
public class CommentResponseDto {

  private Long id;
  private String comment;
  private Long userId;
  private String userName;

  public CommentResponseDto(Comment comment) {
    User user = comment.getUser();
    this.id = comment.getId();
    this.comment = comment.getComment();
    this.userName = user.getUserName();
    this.userId = user.getId();

  }
}
