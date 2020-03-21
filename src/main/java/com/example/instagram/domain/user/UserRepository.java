package com.example.instagram.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  public User findByUserNameAndPassword(String userName, String password);

  User findByUserName(String userName);
}
