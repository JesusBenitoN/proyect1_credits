package com.bootcamp.webflux.proyect1_credits;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.bootcamp.webflux.proyect1_credits.handler.CreditsHandler;

@Configuration
public class RouterFunctionConfig {
	
	@Bean
	public RouterFunction<ServerResponse> routes(CreditsHandler handler){
		
		return route(GET("/api/v2/credits"), request -> handler.list(request))
				.andRoute(GET("/api/v2/credits/{id}"), handler::see)
				.andRoute(POST("/api/v2/credits"), handler::create)
				.andRoute(PUT("/api/v2/credits/{id}"), handler::edit)
				.andRoute(DELETE("/api/v2/credits/{id}"), handler::delete);
	}

}
