package com.example.instagram.domain.posts;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@NoArgsConstructor
@Getter
@Entity
public class Posts {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String caption;

  @Column
  private String likeCount;

  @CreatedDate
  private LocalDateTime createDate;

  @Builder
  public Posts(String caption, String likeCount) {
    this.caption = caption;
    this.likeCount = likeCount;
  }

}
