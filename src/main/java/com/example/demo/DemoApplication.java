package com.example.demo;

import java.time.Duration;

import reactor.blockhound.BlockHound;
import reactor.core.publisher.Mono;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		BlockHound.install();
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	RouterFunction<ServerResponse> route() {
		return RouterFunctions.route()
		                      .GET("/test",
				                      accept(MediaType.APPLICATION_JSON),
				                      this::handler)
		                      .build();
	}

	private Mono<ServerResponse> handler(ServerRequest request) {
		return Mono.just("Hello world!")
		           //.delayElement(Duration.ofMillis(1))
		           .doOnNext(s -> {
			           try {
				           Thread.sleep(100);
			           }
			           catch (InterruptedException e) {
				           e.printStackTrace();
			           }
		           })
		           .onErrorContinue((e, o) -> e.printStackTrace())
		           .flatMap(s -> ServerResponse.ok()
		                                       .contentType(MediaType.APPLICATION_JSON)
		                                       .body(Mono.just(s), String.class));
	}
}
