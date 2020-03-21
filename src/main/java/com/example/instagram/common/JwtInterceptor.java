package com.example.instagram.common;

import com.example.instagram.service.JwtService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Component
public class JwtInterceptor implements HandlerInterceptor {

  private final JwtService jwtService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String token = request.getHeader(CommonConstant.AUTHORIZATION);
    if (token != null && jwtService.valid(token)) {
      request.setAttribute(CommonConstant.USER_ID, jwtService.getUserId(token));
      return true;
    } else {
      throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
    }
  }
}


