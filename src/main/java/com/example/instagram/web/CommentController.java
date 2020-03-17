package com.example.instagram.web;

import com.example.instagram.service.CommentService;
import com.example.instagram.web.dto.CommentRequestDto;
import com.example.instagram.web.dto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentController {

  private final CommentService commentService;

  @PostMapping("/comment")
  public Long save(@RequestBody CommentRequestDto requestDto) {
    return commentService.save(requestDto);
  }

  @GetMapping("/comment/{id}")
  public CommentResponseDto save(@PathVariable Long id) {
    return commentService.find(id);
  }


}
