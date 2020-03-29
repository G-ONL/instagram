package com.example.instagram.web.dto.post;

import com.example.instagram.domain.post.Post;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostSaveRequestDto {

  @ApiModelProperty(notes = "이미지")
  private MultipartFile data;
  @ApiModelProperty(notes = "게시글", example = "첫 게시글~!")
  private String caption;


  public Post toEntity() {
    return Post.builder()
        .caption(caption)
        .build();
  }

  public void inputImg(MultipartFile file) {
    this.data = file;
  }
}
