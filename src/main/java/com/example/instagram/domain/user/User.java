package com.example.instagram.domain.user;

import com.example.instagram.domain.comment.Comment;
import com.example.instagram.domain.like.Likes;
import com.example.instagram.domain.post.Post;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class User {

  @Id
  @Column(name = "USER_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy = "user")
  private List<Comment> comments = new ArrayList<>();

  @OneToMany(mappedBy = "user")
  private List<Post> posts = new ArrayList<>();

  @OneToMany(mappedBy = "user")
  private List<Likes> likes = new ArrayList<>();
}
