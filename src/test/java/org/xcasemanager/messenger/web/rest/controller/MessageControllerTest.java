package org.xcasemanager.messenger.web.rest.controller;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.assertj.core.api.Java6Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.mockito.Mockito.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.xcasemanager.messenger.web.rest.resource.ExecutionMessageDto;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MessageControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(
      new MessageControllerTest()).build();
  }

  @Test
  void whenValidQueuedMessage_thenReturns202() throws Exception {
    ExecutionMessageDto message = new ExecutionMessageDto();
    message.executionId = 1L;
    message.status = "QUEUED";
    mockMvc.perform(post("/add")
            .content(objectMapper.writeValueAsString(message))
            .contentType("application/json"))
            .andExpect(status().isCreated());
  }

  @Test
  void whenValidCompletedMessage_thenReturns202() throws Exception {
    ExecutionMessageDto message = new ExecutionMessageDto();
    message.executionId = 1L;
    message.status = "COMPLETED";
    message.result = "FAILED";
    mockMvc.perform(post("/add")
            .content(objectMapper.writeValueAsString(message))
            .contentType("application/json"))
            .andExpect(status().isCreated());
  }

  @Test
  void whenInvalidMessage_thenReturns400() throws Exception {
    ExecutionMessageDto message = new ExecutionMessageDto();
    message.executionId = 1L;
    message.status = "COMPLETED";
    mockMvc.perform(post("/add")
            .content(objectMapper.writeValueAsString(message))
            .contentType("application/json"))
            .andExpect(status().is4xxClientError());
  }
}