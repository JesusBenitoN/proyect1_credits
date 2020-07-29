
package com.bootcamp.webflux.proyect1_credits.models.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bootcamp.webflux.proyect1_credits.models.documents.Credits;

import reactor.core.publisher.Mono;

public interface CreditsDao extends ReactiveMongoRepository<Credits, String> {

	public Mono<Credits> findByNameCredit(String name);
	
	public Mono<Credits> findByIdCustomer(String idCustomer);
	
}
