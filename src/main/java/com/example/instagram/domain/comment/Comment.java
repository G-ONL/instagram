package com.example.instagram.domain.comment;

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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {

  @Id
  @Column(name = "COMMENT_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String comment;

  @ManyToOne
  @JoinColumn(name="POST_ID")
  private Post post;

  @ManyToOne
  @JoinColumn(name="USER_ID")
  private User user;

  @Builder
  public Comment(String comment) {
    this.comment = comment;
  }

  public void addToPost(Post post) {
    this.post = post;
    if (!post.getComments().contains(this)) {
      post.getComments().add(this);
    }
  }
}
