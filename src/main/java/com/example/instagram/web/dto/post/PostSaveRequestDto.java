package com.example.instagram.web.dto.post;

import com.example.instagram.domain.post.Post;
import com.example.instagram.web.dto.PostPictureRequestDto;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {

  private MultipartFile data;
  private String caption;


  public Post toEntity() {
    return Post.builder()
        .caption(caption)
        .build();
  }

  public void inputImg(MultipartFile file){
    this.data = file;
  }
}
