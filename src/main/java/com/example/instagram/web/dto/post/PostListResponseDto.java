package com.example.instagram.web.dto.post;

import com.example.instagram.domain.post.Post;
import com.example.instagram.web.dto.comment.CommentResponseDto;
import com.example.instagram.web.dto.PostPictureResponseDto;
import com.example.instagram.web.dto.user.UserResponseDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class PostListResponseDto {

  private Long id;
  private String caption;
  private String likeCount;
  private LocalDateTime createdDate;
  private LocalDateTime modifiedDate;

  private List<CommentResponseDto> comments;
  private List<PostPictureResponseDto> postPictures;
  private UserResponseDto user;

  public PostListResponseDto(Post entity) {
    this.id = entity.getId();
    this.caption = entity.getCaption();
    this.likeCount = String.valueOf(entity.getLikes().stream().count());
    this.comments = entity.getComments().stream().map(CommentResponseDto::new)
        .collect(Collectors.toList());
    this.postPictures = entity.getPostPictures().stream().map(PostPictureResponseDto::new)
        .collect(Collectors.toList());
    this.user = new UserResponseDto(entity.getUser());
    this.createdDate = entity.getCreatedDate();
    this.modifiedDate = entity.getModifiedDate();

  }
}
