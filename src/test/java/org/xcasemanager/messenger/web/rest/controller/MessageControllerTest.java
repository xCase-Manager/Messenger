package org.xcasemanager.messenger.web.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.assertj.core.api.Java6Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.xcasemanager.messenger.web.rest.resource.ExecutionMessageDto;

@WebMvcTest(controllers = MessageController.class)
class MessageControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void whenValidMessage_thenReturns201() throws Exception {

    ExecutionMessageDto message = new ExecutionMessageDto(1L, "QUEUED");

    mockMvc.perform(post("/add")
            .content(objectMapper.writeValueAsString(message))
            .contentType("application/json"))
            .andExpect(status().isCreated());
  }

  @Test
  void whenInvalidMessage_thenReturns400() throws Exception {

    ExecutionMessageDto message = new ExecutionMessageDto(1L, "COMPLETED");

    mockMvc.perform(post("/add")
            .content(objectMapper.writeValueAsString(message))
            .contentType("application/json"))
            .andExpect(status().is4xxClientError());
  }
}