package com.example.instagram.domain.user;

import com.example.instagram.common.BaseTimeEntity;
import com.example.instagram.domain.comment.Comment;
import com.example.instagram.domain.like.Likes;
import com.example.instagram.domain.post.Post;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

  @Id
  @Column(name = "USER_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String userName;

  private String password;

  private String location;

  private String avatarUrl;

  @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
  private List<Comment> comments = new ArrayList<>();

  @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
  private List<Post> posts = new ArrayList<>();

  @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
  private List<Likes> likes = new ArrayList<>();

  @Builder
  public User(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }

  public void addAvatar(String pictureUrl) {
    this.avatarUrl = pictureUrl;
  }
}
