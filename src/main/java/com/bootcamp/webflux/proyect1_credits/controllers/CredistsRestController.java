package com.bootcamp.webflux.proyect1_credits.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.webflux.proyect1_credits.models.documents.Credits;
import com.bootcamp.webflux.proyect1_credits.models.services.CreditsService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/credits")
public class CredistsRestController {
	
	@Autowired
	private CreditsService service;
	
	@SuppressWarnings("deprecation")
	@GetMapping
	public Mono<ResponseEntity<Flux<Credits>>> list(){
		return Mono.just(ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(service.findAll()));				
	}
	
	@SuppressWarnings("deprecation")
	@GetMapping("/{id}")
	public Mono<ResponseEntity<Credits>> seeDetail(@PathVariable String id){
		return service.findById(id).map(p -> ResponseEntity.ok()
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping
	public Mono<ResponseEntity<Credits>> create(@RequestBody Credits credits){
		
		return service.save(credits).map(c -> ResponseEntity
					.created(URI.create("/api/credits/".concat(c.getId())))
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.body(c))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@SuppressWarnings("deprecation")
	@PutMapping("/{id}")
	public Mono<ResponseEntity<Credits>> seeDetail(@RequestBody Credits credits, @PathVariable String id){
		return service.findById(id).flatMap(c -> {
			c.setIdCustomer(credits.getIdCustomer());
			c.setNameCredit(credits.getNameCredit());
			return service.save(c);
		}).map(r -> ResponseEntity
				.created(URI.create("/api/credits/".concat(r.getId())))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(r))
			.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id){
		return service.findById(id).flatMap(d -> {
			return service.delete(d).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
		})
		.defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}
}
