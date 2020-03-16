package com.example.instagram.web;

import com.example.instagram.service.PostsService;
import com.example.instagram.web.dto.PostsRequestDto;
import com.example.instagram.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsController {

  private final PostsService postsService;

  @GetMapping("/posts/{id}")
  public PostsResponseDto find(@PathVariable("id") Long id) {
    return postsService.find(id);
  }

  @PostMapping("/posts")
  public Long save(PostsRequestDto postsRequestDto) {
    return postsService.save(postsRequestDto);
  }
}
