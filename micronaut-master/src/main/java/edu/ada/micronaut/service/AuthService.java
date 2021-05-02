package edu.ada.micronaut.service;

public interface AuthService {
	boolean register(String username, String password);
	String loginAndGetToken(String username, String password);
	boolean isTokenValid(String token);
	String generateToken();
}
