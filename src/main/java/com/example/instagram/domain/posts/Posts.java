package com.example.instagram.domain.posts;

import com.example.instagram.common.BaseTimeEntity;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String caption;

  private String likeCount;

  @Builder
  public Posts(String caption, String likeCount) {
    this.caption = caption;
    this.likeCount = likeCount;
  }

  public void update(String caption, String likeCount) {
    this.caption = caption;
    this.likeCount = likeCount;
  }
}
