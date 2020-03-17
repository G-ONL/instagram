package com.example.instagram.service;

import com.example.instagram.domain.post.Post;
import com.example.instagram.domain.post.PostRepository;
import com.example.instagram.web.dto.PostSaveRequestDto;
import com.example.instagram.web.dto.PostResponseDto;
import com.example.instagram.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

  private final PostRepository postRepository;

  @Transactional
  public Long save(PostSaveRequestDto postSaveRequestDto) {
    return postRepository.save(postSaveRequestDto.toEntity()).getId();
  }

  @Transactional(readOnly = true)
  public PostResponseDto find(Long id) {
    Post post = postRepository.findById(id).orElseThrow(()
        -> new IllegalArgumentException("해당 포스트가 없습니다."));
    return new PostResponseDto(post);
  }

  @Transactional
  public Long update(Long id, PostUpdateRequestDto updateRequestDto) {
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 포스트가 없습니다."));
    post.update(updateRequestDto.getCaption(), updateRequestDto.getLikeCount());
    return id;
  }

  @Transactional
  public void delete(Long id) {
    Post post = postRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("해당 포스트가 없습니다."));
    postRepository.delete(post);
  }
}
