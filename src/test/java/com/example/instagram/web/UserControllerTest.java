package com.example.instagram.web;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.instagram.domain.user.UserRepository;
import com.example.instagram.web.dto.UserJoinRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TestRestTemplate testRestTemplate;

  @After
  public void tearDown() throws Exception {
    userRepository.deleteAll();
  }


  @Test
  public void 유저가_로그인_성공() throws Exception {
    //given
    String userName = "abc@naver.com";
    String password = "12345";
    String url = "http://localhost:" + port + "/user/join";
    UserJoinRequestDto userJoinRequestDto = new UserJoinRequestDto(userName, password);

    //when
    ResponseEntity responseEntity = testRestTemplate
        .postForEntity(url, userJoinRequestDto, Long.class);
    //then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isEqualTo(1L);
  }
}
