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


import org.springframework.context.annotation.ComponentScan;
import org.junit.Before;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@WebMvcTest(controllers = MessageController.class)
@ComponentScan(basePackages = {"org.xcasemanager.messenger"})
class MessageControllerTest {

  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(
      new MessageControllerTest()).build();
  }

  @Test
  public void testHomePage() throws Exception {
    this.mockMvc.perform(get("/"))
      .andExpect(status().isOk())
      .andExpect(view().name("index"))
      .andDo(MockMvcResultHandlers.print())
      .andReturn();
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