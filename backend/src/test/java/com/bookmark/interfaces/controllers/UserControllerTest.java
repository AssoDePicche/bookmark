package com.bookmark.interfaces.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bookmark.interfaces.dto.user.UserRegistrationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {
  private static final MediaType contentType = MediaType.APPLICATION_JSON;

  private static final String URL = "/api/users";

  private static final Faker faker = new Faker();

  @Autowired private MockMvc mvc;

  @Autowired private ObjectMapper mapper;

  @Test
  void validUserRegistrationRequestShouldReturnCreated() throws Exception {
    UserRegistrationRequest request = new UserRegistrationRequest(faker.internet().emailAddress(),
        faker.name().username(), faker.internet().password(), "user");

    String json = mapper.writeValueAsString(request);

    mvc.perform(
           MockMvcRequestBuilders.post(URL + "/register").contentType(contentType).content(json))
        .andExpect(status().isCreated());
  }
}
