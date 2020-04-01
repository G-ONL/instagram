package com.example.instagram.web;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.instagram.domain.user.UserRepository;
import com.example.instagram.service.UserService;
import com.example.instagram.web.dto.ResponseDataDto;
import com.example.instagram.web.dto.user.UserJoinRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Autowired
  private UserService userService;

  @After
  public void tearDown() {
    userRepository.deleteAll();
  }

  @Test
  public void 유저가_회원가입_성공() {
    //given
    String userName = "abc@naver.com";
    String password = "12345";
    String url = "/user/join";
    UserJoinRequestDto userJoinRequestDto = new UserJoinRequestDto(userName, password);

    //when
    ResponseEntity responseEntity = testRestTemplate
        .postForEntity(url, userJoinRequestDto, ResponseDataDto.class);

    //then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    ResponseDataDto responseDataDto = (ResponseDataDto) responseEntity.getBody();
    assertThat(responseDataDto).isInstanceOf(ResponseDataDto.class);
    assertThat(responseDataDto.getStatus()).isEqualTo(200);
    assertThat(responseDataDto.getMessage()).isEqualTo("Success");

  }

  @Test
  public void 중복_회원_가입() {
    //given
    String userName = "abc@naver.com";
    String password = "12345";
    String url = "/user/join";
    UserJoinRequestDto userJoinRequestDto = new UserJoinRequestDto(userName, password);
    UserJoinRequestDto duplicateUser = new UserJoinRequestDto(userName, password);
    userService.join(userJoinRequestDto);
    //when
    ResponseEntity responseEntity = testRestTemplate
        .postForEntity(url, duplicateUser, ResponseDataDto.class);
    //then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    ResponseDataDto responseDataDto = (ResponseDataDto) responseEntity.getBody();
    assertThat(responseDataDto).isInstanceOf(ResponseDataDto.class);
    assertThat(responseDataDto.getStatus()).isEqualTo(400);
    assertThat(responseDataDto.getMessage()).isEqualTo("이미 등록된 아이디 입니다.");

  }

}
