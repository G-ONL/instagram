package com.example.instagram.domain.postPicture;

import com.example.instagram.domain.post.Post;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class PostPicture {

  @Id
  @Column(name = "POST_PICTURE_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

}
