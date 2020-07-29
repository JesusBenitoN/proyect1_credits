package com.bootcamp.webflux.proyect1_credits;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.bootcamp.webflux.proyect1_credits.models.documents.Credits;
import com.bootcamp.webflux.proyect1_credits.models.services.CreditsService;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class Proyect1CreditsApplication implements CommandLineRunner {
	
	@Autowired
	private CreditsService service;
	
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(Proyect1CreditsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(Proyect1CreditsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		mongoTemplate.dropCollection("credits").subscribe();
		
		Flux.just(new Credits("qwe9888", "Credito Personal", 0.00, 0.00, 900.00),
				new Credits("poi89000", "Credito Empresarial", 0.00, 0.00, 800.00),
				new Credits("ppp90989", "Tarjeta de Credito", 0.00, 0.00, 700.00),
				new Credits("qwe0990", "Adelanto de Efectivo", 0.00, 0.00, 8500.00))
		.flatMap(credits -> {
			return service.save(credits);
		})
		.subscribe(credits -> log.info("Insert NameCredit: "+ credits.getNameCredit()));
	}

}
