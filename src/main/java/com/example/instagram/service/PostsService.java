package com.example.instagram.service;

import com.example.instagram.domain.posts.Posts;
import com.example.instagram.domain.posts.PostsRepository;
import com.example.instagram.web.dto.PostsSaveRequestDto;
import com.example.instagram.web.dto.PostsResponseDto;
import com.example.instagram.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

  private final PostsRepository postsRepository;

  @Transactional
  public Long save(PostsSaveRequestDto postsSaveRequestDto) {
    return postsRepository.save(postsSaveRequestDto.toEntity()).getId();
  }

  @Transactional(readOnly = true)
  public PostsResponseDto find(Long id) {
    Posts posts = postsRepository.findById(id).orElseThrow(()
        -> new IllegalArgumentException("해당 포스트가 없습니다."));
    return new PostsResponseDto(posts);
  }

  @Transactional
  public Long update(Long id, PostsUpdateRequestDto updateRequestDto) {
    Posts posts = postsRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 포스트가 없습니다."));
    posts.update(updateRequestDto.getCaption(), updateRequestDto.getLikeCount());
    return id;
  }

  @Transactional
  public void delete(Long id) {
    Posts posts = postsRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("해당 포스트가 없습니다."));
    postsRepository.delete(posts);
  }
}
