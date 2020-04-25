package com.example.instagram.service;

import com.example.instagram.domain.comment.Comment;
import com.example.instagram.domain.comment.CommentRepository;
import com.example.instagram.domain.post.Post;
import com.example.instagram.domain.post.PostRepository;
import com.example.instagram.domain.user.User;
import com.example.instagram.domain.user.UserRepository;
import com.example.instagram.web.dto.comment.CommentRequestDto;
import com.example.instagram.web.dto.comment.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

  private final PostRepository postRepository;
  private final CommentRepository commentRepository;
  private final UserRepository userRepository;

  @Transactional
  public Long save(CommentRequestDto commentRequestDto, Long userId) {
    User user = userRepository.findById(userId).orElseThrow(
        () -> new IllegalArgumentException("해당하는 유저가 없습니다.")
    );
    Post post = postRepository.findById(commentRequestDto.getPostId()).orElseThrow(
        () -> new IllegalArgumentException("해당하는 포스트가 없습니다.")
    );
    Comment comment = commentRequestDto.toEntity();
    comment.addToUser(user);
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
