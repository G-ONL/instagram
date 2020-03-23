package com.example.instagram.web;

import com.example.instagram.common.CommonConstant;
import com.example.instagram.service.PostService;
import com.example.instagram.web.dto.post.PostSaveRequestDto;
import com.example.instagram.web.dto.post.PostResponseDto;
import com.example.instagram.web.dto.post.PostUpdateRequestDto;
import com.example.instagram.web.dto.post.PostsListResponseDto;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostController {

  private final PostService postService;

  @PutMapping("/api/v1/posts/{id}")
  public Long find(@PathVariable Long id, @RequestBody PostUpdateRequestDto updateRequestDto) {
    return postService.update(id, updateRequestDto);
  }

  @GetMapping("/api/v1/posts/{id}")
  public PostResponseDto find(@PathVariable Long id) {
    return postService.find(id);
  }

  @PostMapping("/api/v1/posts")
  public Long save(@RequestBody PostSaveRequestDto saveRequestDto, HttpServletRequest request) {
    Long userId = Long.valueOf(String.valueOf(request.getAttribute(CommonConstant.USER_ID)));
    return postService.save(saveRequestDto, userId);
  }

  @DeleteMapping("/api/v1/posts/{id}")
  public Long delete(@PathVariable Long id) {
    postService.delete(id);
    return id;
  }

  @GetMapping("/api/v1/posts")
  public PostsListResponseDto find() {
    return postService.findAll();
  }
}
