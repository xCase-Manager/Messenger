package org.xcasemanager.messenger.web.rest.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

	@Autowired
	private MessageController controller;

	@Test
	public void contextLoads() throws Exception {
		Assertions.assertNotNull(controller);
	}
}