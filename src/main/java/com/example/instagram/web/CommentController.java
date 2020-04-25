package com.example.instagram.web;

import com.example.instagram.common.CommonConstant;
import com.example.instagram.service.CommentService;
import com.example.instagram.web.dto.ResponseDataDto;
import com.example.instagram.web.dto.comment.CommentRequestDto;
import io.swagger.annotations.Api;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "댓글", tags="Comment")
@RequiredArgsConstructor
@RestController
public class CommentController {

  private final CommentService commentService;

  @PostMapping("/api/v1/comment")
  public ResponseEntity<ResponseDataDto> save(@RequestBody CommentRequestDto requestDto,  HttpServletRequest request) {
    Long userId = Long.valueOf(String.valueOf(request.getAttribute(CommonConstant.USER_ID)));
    commentService.save(requestDto,userId);
    return ResponseEntity.ok(new ResponseDataDto(HttpStatus.OK.value()));
  }

  @GetMapping("/api/v1/comment/{id}")
  public ResponseEntity<ResponseDataDto> save(@PathVariable Long id) {
    return ResponseEntity.ok(new ResponseDataDto(HttpStatus.OK.value(), commentService.find(id)));
  }
}
