package com.example.instagram.web;

import com.example.instagram.common.CommonConstant;
import com.example.instagram.service.PostService;
import com.example.instagram.web.dto.ResponseDataDto;
import com.example.instagram.web.dto.ResponseMessageDto;
import com.example.instagram.web.dto.post.PostSaveRequestDto;
import com.example.instagram.web.dto.post.PostUpdateRequestDto;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<ResponseMessageDto> find(@PathVariable Long id,
      @RequestBody PostUpdateRequestDto updateRequestDto) {
    postService.update(id, updateRequestDto);
    return ResponseEntity.ok(new ResponseMessageDto(HttpStatus.OK.value()));
  }

  @GetMapping("/api/v1/posts/{id}")
  public ResponseEntity<ResponseDataDto> find(@PathVariable Long id) {
    return ResponseEntity.ok(new ResponseDataDto(HttpStatus.OK.value(), postService.find(id)));
  }

  @PostMapping("/api/v1/posts")
  public ResponseEntity<ResponseMessageDto> save(@RequestBody PostSaveRequestDto saveRequestDto,
      HttpServletRequest request) {
    Long userId = Long.valueOf(String.valueOf(request.getAttribute(CommonConstant.USER_ID)));
    postService.save(saveRequestDto, userId);
    return ResponseEntity.ok(new ResponseMessageDto(HttpStatus.OK.value()));

  }

  @DeleteMapping("/api/v1/posts/{id}")
  public ResponseEntity<ResponseMessageDto> delete(@PathVariable Long id) {
    postService.delete(id);
    return ResponseEntity.ok(new ResponseMessageDto(HttpStatus.OK.value()));
  }

  @GetMapping("/api/v1/posts")
  public ResponseEntity<ResponseDataDto> find() {
    return ResponseEntity.ok(new ResponseDataDto(HttpStatus.OK.value(), postService.findAll()));
  }
}
