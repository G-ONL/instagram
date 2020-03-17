package com.example.instagram.domain.post;

import com.example.instagram.common.BaseTimeEntity;
import com.example.instagram.domain.comment.Comment;
import com.example.instagram.domain.user.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity {

  @Id
  @Column(name = "POST_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String caption;

  private String likeCount;

  @OneToMany(mappedBy = "post")
  private List<Comment> comments = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name="user_id")
  private User user;

  @Builder
  public Post(String caption, String likeCount) {
    this.caption = caption;
    this.likeCount = likeCount;
  }

  public void update(String caption, String likeCount) {
    this.caption = caption;
    this.likeCount = likeCount;
  }

}
