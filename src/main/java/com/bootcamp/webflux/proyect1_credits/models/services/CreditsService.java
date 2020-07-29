package com.bootcamp.webflux.proyect1_credits.models.services;

import com.bootcamp.webflux.proyect1_credits.models.documents.Credits;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditsService {
	
	public Flux<Credits> findAll();
	
	public Mono<Credits> findById(String id);
	
	public Mono<Credits> save(Credits credits);
	
	public Mono<Void> delete(Credits credits);
	
	public Mono<Credits> findByNameCredit(String name);
	
	public Mono<Credits> findByIdCustomer(String idCustomer);

}
