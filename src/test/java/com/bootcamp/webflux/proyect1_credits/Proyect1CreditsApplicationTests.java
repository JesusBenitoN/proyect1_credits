package com.bootcamp.webflux.proyect1_credits;

import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.bootcamp.webflux.proyect1_credits.models.documents.Credits;
import com.bootcamp.webflux.proyect1_credits.models.services.CreditsService;

import reactor.core.publisher.Mono;
//para el mock
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)//RANDOM_PORT
class Proyect1CreditsApplicationTests {

	@Autowired
	private WebTestClient client;
		
	@Autowired
	private CreditsService service;

	@SuppressWarnings("deprecation")
	@Test
	void listTest() {
		
		client.get()
		.uri("/api/credits")
		.accept(MediaType.APPLICATION_JSON_UTF8)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
		.expectBodyList(Credits.class)
		.consumeWith(response -> {
			List<Credits> credits = response.getResponseBody();
			credits.forEach(c -> {
				System.out.print(c.getNameCredit());
			});
			Assertions.assertThat(credits.size()>0).isTrue();
		});
	}
	
	@SuppressWarnings("deprecation")
	@Test
	void seeTest() {
		
		Credits credits = service.findByIdCustomer("qwe9888").block();
		
		client.get()
		.uri("/api/credits/{id}", Collections.singletonMap("id", credits.getId()))
		.accept(MediaType.APPLICATION_JSON_UTF8)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
		//cuando el body es un objeto
		.expectBody(Credits.class)
		.consumeWith(response -> {
			Credits c = response.getResponseBody();
			Assertions.assertThat(c.getId()).isNotEmpty();
			Assertions.assertThat(c.getIdCustomer()).isEqualTo("qwe9888");
		});
	}
	
	@SuppressWarnings("deprecation")
	@Test
	void createTest() {
		
		Credits credits = new Credits("pop9876", "Credito Empresarial", 0.00, 0.00, 800.00);
		client.post()
		.uri("/api/credits")
		.accept(MediaType.APPLICATION_JSON_UTF8)
		.contentType(MediaType.APPLICATION_JSON_UTF8)
		.body(Mono.just(credits), Credits.class)
		.exchange()
		.expectStatus().isCreated()
		.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
		.expectBody(Credits.class)
		.consumeWith(response -> {
			Credits c = response.getResponseBody();
			Assertions.assertThat(c.getId()).isNotEmpty();
			Assertions.assertThat(c.getIdCustomer()).isEqualTo("pop9876");
			Assertions.assertThat(c.getNameCredit()).isEqualTo("Credito Empresarial");
		});

	}

	@SuppressWarnings("deprecation")
	@Test
	void editTest() {
		Credits credits = service.findByIdCustomer("qwe0990").block();
		
		Credits creditsEdit = new Credits("qwe0990", "Credito Personal", 0.00, 0.00, 8000.00);
		client.put()
		.uri("/api/credits/{id}", Collections.singletonMap("id", credits.getId()))
		.accept(MediaType.APPLICATION_JSON_UTF8)
		.contentType(MediaType.APPLICATION_JSON_UTF8)
		.body(Mono.just(creditsEdit), Credits.class)
		.exchange()
		.expectStatus().isCreated()
		.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
		.expectBody(Credits.class)
		.consumeWith(response -> {
			Credits c = response.getResponseBody();
			Assertions.assertThat(c.getId()).isNotEmpty();
			Assertions.assertThat(c.getNameCredit()).isEqualTo("Credito Personal");
		});
	}
	
	@Test
	void deleteTest() {
		Credits credits = service.findByIdCustomer("poi89000").block();
		
		client.delete()
		.uri("/api/credits/{id}", Collections.singletonMap("id", credits.getId()))
		.exchange()
		.expectStatus().isNoContent()
		.expectBody()
		.isEmpty();
		
	}

}
