package com.example.instagram.exception;

public class JwtException extends RuntimeException {

  public JwtException(String message) {
    super(message);
  }
}
