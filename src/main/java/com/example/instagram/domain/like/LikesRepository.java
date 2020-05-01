package com.example.instagram.domain.like;

import com.example.instagram.domain.post.Post;
import com.example.instagram.domain.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {
  public Optional<Likes> findByPostAndUser(Post post, User user);


}
