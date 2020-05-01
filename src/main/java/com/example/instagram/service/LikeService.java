package com.example.instagram.service;

import com.example.instagram.domain.like.Likes;
import com.example.instagram.domain.like.LikesRepository;
import com.example.instagram.domain.post.Post;
import com.example.instagram.domain.post.PostRepository;
import com.example.instagram.domain.user.User;
import com.example.instagram.domain.user.UserRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
public class LikeService {

  private final PostRepository postRepository;
  private final UserRepository userRepository;
  private final LikesRepository likesRepository;

  @Transactional
  public Long save(Long postId, Long userId) {
    Post post = postRepository.findById(postId).orElseThrow(
        () -> new IllegalArgumentException("존재하지 않는 포스트 입니다.")
    );
    User user = userRepository.findById(userId).orElseThrow(
        () -> new IllegalArgumentException("존재하지 않는 유저 입니다.")
    );
    Optional<Likes> likeOptional = likesRepository.findByPostAndUser(post, user);

    if (likeOptional.isPresent()) {
      likesRepository.delete(likeOptional.get());
      return postId;
    }
    Likes likes = Likes.builder().post(post).user(user).build();
    likes.addToPostAndUser(user, post);
    likesRepository.save(likes);
    return postId;
  }


}
