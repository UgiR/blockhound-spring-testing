package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
@ExtendWith(SpringExtension.class)
class DemoApplicationTests {

	@Autowired
	WebTestClient client;

	@Test
	void queryBlockingEndpoint() {
		client.get()
		      .uri("/test")
		      .exchange()
		      .expectBody(String.class)
		      .isEqualTo("Hello world!");
	}
}
