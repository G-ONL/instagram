package com.example.instagram.web;

import com.example.instagram.service.PostService;
import com.example.instagram.web.dto.PostSaveRequestDto;
import com.example.instagram.web.dto.PostResponseDto;
import com.example.instagram.web.dto.PostUpdateRequestDto;
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

  @PutMapping("/posts/{id}")
  public Long find(@PathVariable Long id, @RequestBody PostUpdateRequestDto updateRequestDto) {
    return postService.update(id, updateRequestDto);
  }

  @GetMapping("/posts/{id}")
  public PostResponseDto find(@PathVariable Long id) {
    return postService.find(id);
  }

  @PostMapping("/posts")
  public Long save(@RequestBody PostSaveRequestDto saveRequestDto) {
    return postService.save(saveRequestDto);
  }

  @DeleteMapping("/posts/{id}")
  public Long delete(@PathVariable Long id) {
    postService.delete(id);
    return id;
  }

}
