package com.bootcamp.webflux.proyect1_credits.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.webflux.proyect1_credits.models.dao.CreditsDao;
import com.bootcamp.webflux.proyect1_credits.models.documents.Credits;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditsServiceImpl implements CreditsService{
	
	@Autowired
	private CreditsDao dao;

	@Override
	public Flux<Credits> findAll() {
		return dao.findAll();
	}

	@Override
	public Mono<Credits> findById(String id) {
		return dao.findById(id);
	}

	@Override
	public Mono<Credits> save(Credits credits) {
		return dao.save(credits);
	}

	@Override
	public Mono<Void> delete(Credits credits) {
		return dao.delete(credits);
	}

	@Override
	public Mono<Credits> findByNameCredit(String name) {
		return dao.findByNameCredit(name);
	}

	@Override
	public Mono<Credits> findByIdCustomer(String idCustomer) {
		return dao.findByIdCustomer(idCustomer);
	}

}
