package com.example.instagram.service;

import com.example.instagram.common.CommonConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JwtService {

  Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

  public <T> String create(T data) {
    String jws = Jwts.builder()
        .claim(CommonConstant.USER_ID, data)
        .setExpiration(new Date(System.currentTimeMillis() + 1 * (1000 * 60 * 60 * 24)))
        .signWith(key)
        .compact();
    return jws;
  }

  public boolean valid(String jws) {
    try {
      Date expiration = Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(jws)
          .getBody()
          .getExpiration();
      Date now = new Date();
      if (now.after(expiration)) {
        return false;
      }
      return true;
    } catch (JwtException e) {
      log.debug("================ getUserName fail ============");
      log.debug(e.getMessage());
      return false;
    }
  }

  public Integer getUserId(String jws) {
    try {
      Integer userId = (Integer) Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(jws)
          .getBody()
          .get(CommonConstant.USER_ID);
      return userId;
    } catch (JwtException e) {
      log.debug("================ getUserId fail ============");
      log.debug(e.getMessage());
      return null;
    }
  }

}
