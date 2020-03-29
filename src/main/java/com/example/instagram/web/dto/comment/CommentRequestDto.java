package com.example.instagram.web.dto.comment;


import com.example.instagram.domain.comment.Comment;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class CommentRequestDto {

  @ApiModelProperty(notes = "유저ID", example = "1")
  private Long userId;
  @ApiModelProperty(notes = "포스트ID", example = "1")
  private Long postId;
  @ApiModelProperty(notes = "댓글", example = "댓글 입니다.")
  private String comment;

  public Comment toEntity() {
    return Comment.builder()
        .comment(comment)
        .build();
  }
}
