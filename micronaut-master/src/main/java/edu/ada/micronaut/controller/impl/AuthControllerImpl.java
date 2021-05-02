package edu.ada.micronaut.controller.impl;

import edu.ada.micronaut.controller.AuthController;
import edu.ada.micronaut.service.AuthService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;

import javax.inject.Inject;

@Controller("/auth")
public class AuthControllerImpl implements AuthController {
	@Inject
	private AuthService authService;
	
	@Override
	@Post("/register")
	@Produces(MediaType.APPLICATION_JSON)
	public Object register(
			@Header String username,
			@Header String password) {
		if (authService.register(username, password)) return HttpResponse.ok();
		return HttpResponse.badRequest();
	}
	
	@Override
	@Post("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Object login(
			@Header String username,
			@Header String password) {
		String token = authService.loginAndGetToken(username, password);
		if (token != null) return HttpResponse.ok(token);
		return HttpResponse.notFound();
	}
}
