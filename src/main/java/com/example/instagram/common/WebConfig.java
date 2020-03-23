package com.example.instagram.common;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

  private final JwtInterceptor jwtInterceptor;
  private static final String[] EXCLUDE_PATHS = {"/resource/**","/static/**", "/user/**", "/error/**"};

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(jwtInterceptor).addPathPatterns("/api/**")
        .excludePathPatterns(EXCLUDE_PATHS);
  }
}
