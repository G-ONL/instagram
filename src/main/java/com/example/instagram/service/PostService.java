package com.example.instagram.service;

//import com.example.instagram.common.S3Uploader;

import com.example.instagram.common.S3Uploader;
import com.example.instagram.domain.post.Post;
import com.example.instagram.domain.post.PostRepository;
import com.example.instagram.domain.postPicture.PostPicture;
import com.example.instagram.domain.postPicture.PostPictureRepository;
import com.example.instagram.domain.user.User;
import com.example.instagram.domain.user.UserRepository;
import com.example.instagram.exception.PostException;
import com.example.instagram.exception.UserException;
import com.example.instagram.web.dto.PostPictureRequestDto;
import com.example.instagram.web.dto.post.PostListResponseDto;
import com.example.instagram.web.dto.post.PostResponseDto;
import com.example.instagram.web.dto.post.PostSaveRequestDto;
import com.example.instagram.web.dto.post.PostUpdateRequestDto;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

  private final PostRepository postRepository;
  private final UserRepository userRepository;
  private final PostPictureRepository postPictureRepository;
  private final S3Uploader s3Uploader;

  @Transactional
  public Long save(PostSaveRequestDto postSaveRequestDto, Long userId) throws IOException {
    User user = userRepository.findById(userId).orElseThrow(
        () -> new UserException("존재하지 않는 유저 입니다.")
    );
    Post post = postSaveRequestDto.toEntity();
    post.addToUser(user);
    postRepository.save(post);
    savePostPicture(postSaveRequestDto, post);
    return post.getId();
  }
  
  private void savePostPicture(PostSaveRequestDto postSaveRequestDto, Post post)
      throws IOException {
    String pictureUrl = s3Uploader.upload(postSaveRequestDto.getData(), "static");
    PostPictureRequestDto request = new PostPictureRequestDto(pictureUrl);
    PostPicture postPicture = request.toEntity();
    postPicture.addToPost(post);
    postPictureRepository.save(postPicture);
  }

  @Transactional(readOnly = true)
  public PostResponseDto find(Long id) {
    Post post = postRepository.findById(id).orElseThrow(()
        -> new PostException("해당 포스트가 없습니다."));
    return new PostResponseDto(post);
  }

  @Transactional
  public Long update(Long id, PostUpdateRequestDto updateRequestDto) {
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new PostException("해당 포스트가 없습니다."));
    post.update(updateRequestDto.getCaption(), updateRequestDto.getLikeCount());
    return id;
  }

  @Transactional
  public void delete(Long id) {
    Post post = postRepository.findById(id).orElseThrow(
        () -> new PostException("해당 포스트가 없습니다."));
    postRepository.delete(post);
  }

  public List<PostListResponseDto> findAll() {
    log.info("========== Get : /api/v1/posts 호출");
    return postRepository.findAll().stream().map(PostListResponseDto::new)
        .collect(Collectors.toList());

  }
}
