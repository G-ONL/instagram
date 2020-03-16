package com.example.instagram.service;

import com.example.instagram.domain.posts.Posts;
import com.example.instagram.domain.posts.PostsRepository;
import com.example.instagram.web.dto.PostsRequestDto;
import com.example.instagram.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

  private final PostsRepository postsRepository;

  public Long save(PostsRequestDto postsRequestDto) {
    return postsRepository.save(postsRequestDto.toEntity()).getId();
  }

  @Transactional(readOnly = true)
  public PostsResponseDto find(Long id) {
    Posts posts = postsRepository.findById(id).orElseThrow(()
        -> new IllegalArgumentException("해당 포스트가 없습니다."));
    return new PostsResponseDto(posts);
  }
}
