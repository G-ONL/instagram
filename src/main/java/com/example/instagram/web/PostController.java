package com.example.instagram.web;

import com.example.instagram.common.CommonConstant;
import com.example.instagram.service.PostService;
import com.example.instagram.web.dto.ResponseDataDto;
import com.example.instagram.web.dto.ResponseMessageDto;
import com.example.instagram.web.dto.post.PostSaveRequestDto;
import com.example.instagram.web.dto.post.PostUpdateRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Api(description = "게시글", tags = "Post")
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

  @ApiOperation(value = "게시글 개별 조회")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "id", value = "게시글 id", required = true, dataType = "string", paramType = "path", defaultValue = "1")
  })
  @GetMapping("/api/v1/posts/{id}")
  public ResponseEntity<ResponseDataDto> find(@PathVariable Long id) {
    return ResponseEntity.ok(new ResponseDataDto(HttpStatus.OK.value(), postService.find(id)));
  }

  @ApiOperation(value = "게시글 작성")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "caption", value = "게시글", required = true, dataType = "string", defaultValue = "게시글 입니다.")
  })

  @PostMapping("/api/v1/posts")
  public ResponseEntity<ResponseMessageDto> save(@RequestParam("file") MultipartFile[] files,
      @RequestParam("caption") String caption,
      HttpServletRequest request) throws IOException {
    log.debug("========== Post : /api/v1/posts 호출");

    PostSaveRequestDto saveRequestDto = new PostSaveRequestDto(files[0], caption);
    if (saveRequestDto.getData() == null) {
      saveRequestDto.inputImg(files[0]);
    }
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
    log.debug("========== Get : /api/v1/posts 호출");
    return ResponseEntity.ok(new ResponseDataDto(HttpStatus.OK.value(), postService.findAll()));
  }
}
