package com.example.instagram.web;

import com.example.instagram.common.CommonConstant;
import com.example.instagram.service.LikeService;
import com.example.instagram.web.dto.ResponseDataDto;
import com.example.instagram.web.dto.ResponseListDataDto;
import io.swagger.annotations.Api;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(description = "좋아요", tags = "Like")
@RequiredArgsConstructor
@RestController
public class LikeController {

  private final LikeService likeService;

  @GetMapping("/api/v1/likes/{postId}")
  public ResponseEntity<ResponseDataDto> like(@PathVariable Long postId,  HttpServletRequest request) {
    Long userId = Long.valueOf(String.valueOf(request.getAttribute(CommonConstant.USER_ID)));
    return ResponseEntity.ok(new ResponseDataDto(HttpStatus.OK.value(), likeService.save(postId, userId)));
  }

}
