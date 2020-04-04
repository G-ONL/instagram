package com.example.instagram.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.instagram.domain.user.User;
import com.example.instagram.domain.user.UserRepository;
import com.example.instagram.service.UserService;
import com.example.instagram.web.dto.user.UserJoinRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

  @Autowired
  MockMvc mvc;

  @Autowired
  UserService userService;
  @Autowired
  UserRepository userRepository;

  @AfterEach
  public void tearDown() {
  }

  @Test
  public void Controller_get_Name() {
    String userName = "abc@naver.com";
    String password = "12345";
    UserJoinRequestDto userJoinRequestDto = new UserJoinRequestDto(userName, password);

    userService.join(userJoinRequestDto);

    User user = userRepository.findByUserName(userName);
    assertThat(user).isNotNull();
  }

  @Test
  public void 유저가_회원가입_성공() throws Exception {
    //given
    String userName = "abc@naver.com";
    String password = "12345";
    UserJoinRequestDto userJoinRequestDto = new UserJoinRequestDto(userName, password);

    //when
    mvc.perform(post("/user/join")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(new ObjectMapper().writeValueAsString(userJoinRequestDto)))
        .andDo(print())
        .andExpect(status().isOk());

  }
//
//  @Test
//  public void 중복_회원_가입() {
//    //given
//    String userName = "abc@naver.com";
//    String password = "12345";
//    String url = "/user/join";
//    UserJoinRequestDto userJoinRequestDto = new UserJoinRequestDto(userName, password);
//    UserJoinRequestDto duplicateUser = new UserJoinRequestDto(userName, password);
//    userService.join(userJoinRequestDto);
//  }

}