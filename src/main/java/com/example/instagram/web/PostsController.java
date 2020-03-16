package com.example.instagram.web;

import com.example.instagram.service.PostsService;
import com.example.instagram.web.dto.PostsSaveRequestDto;
import com.example.instagram.web.dto.PostsResponseDto;
import com.example.instagram.web.dto.PostsUpdateRequestDto;
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
public class PostsController {

  private final PostsService postsService;

  @PutMapping("/posts/{id}")
  public Long find(@PathVariable Long id, @RequestBody PostsUpdateRequestDto updateRequestDto) {
    return postsService.update(id, updateRequestDto);
  }

  @GetMapping("/posts/{id}")
  public PostsResponseDto find(@PathVariable Long id) {
    return postsService.find(id);
  }

  @PostMapping("/posts")
  public Long save(@RequestBody PostsSaveRequestDto saveRequestDto) {
    return postsService.save(saveRequestDto);
  }

  @DeleteMapping("/posts/{id}")
  public Long delete(@PathVariable Long id) {
    postsService.delete(id);
    return id;
  }
}
