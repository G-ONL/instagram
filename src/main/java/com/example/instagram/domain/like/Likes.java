package com.example.instagram.domain.like;

import com.example.instagram.common.BaseTimeEntity;
import com.example.instagram.domain.post.Post;
import com.example.instagram.domain.user.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Likes extends BaseTimeEntity {

  @Id
  @Column(name = "LIKE_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "USER_ID")
  private User user;

  @ManyToOne
  @JoinColumn(name = "POST_ID")
  private Post post;


  @Builder
  public Likes(User user, Post post) {
    this.user = user;
    this.post = post;
  }

  public void addToPostAndUser(User user, Post post) {
    this.user = user;
    this.post = post;
    if (!post.getLikes().contains(this)) {
      post.getLikes().add(this);
    }
    if (!user.getLikes().contains(this)) {
      user.getLikes().add(this);
    }
  }

}
