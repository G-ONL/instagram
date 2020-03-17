package com.example.instagram.domain.postPicture;

import com.example.instagram.common.BaseTimeEntity;
import com.example.instagram.domain.post.Post;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class PostPicture extends BaseTimeEntity {

  @Id
  @Column(name = "POST_PICTURE_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String pictureUrl;

  @ManyToOne
  @JoinColumn(name = "POST_ID")
  private Post post;

  @Builder
  public PostPicture(String pictureUrl) {
    this.pictureUrl = pictureUrl;
  }

  public void addToUser(Post post) {
    this.post = post;
    if (!post.getPostPictures().contains(this)) {
      post.getPostPictures().add(this);
    }
  }
}
