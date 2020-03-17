package com.example.instagram.service;

import com.example.instagram.domain.comment.Comment;
import com.example.instagram.domain.comment.CommentRepository;
import com.example.instagram.domain.post.Post;
import com.example.instagram.domain.post.PostRepository;
import com.example.instagram.web.dto.CommentRequestDto;
import com.example.instagram.web.dto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

  private final PostRepository postRepository;
  private final CommentRepository commentRepository;

  @Transactional
  public Long save(CommentRequestDto commentRequestDto) {
    Post post = postRepository.findById(commentRequestDto.getPostId()).orElseThrow(
        () -> new IllegalArgumentException("해당하는 포스트가 없습니다.")
    );
    Comment comment = Comment.builder()
        .comment(commentRequestDto.getComment())
        .build();
    comment.addToPost(post);
    return commentRepository.save(comment).getId();
  }

  public CommentResponseDto find(Long id) {
    Comment comment = commentRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("해당하는 코멘트가 없습니다.")
    );
    return new CommentResponseDto(comment);
  }
}
