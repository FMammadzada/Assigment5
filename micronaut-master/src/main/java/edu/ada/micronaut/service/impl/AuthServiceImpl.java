package edu.ada.micronaut.service.impl;

import edu.ada.micronaut.service.AuthService;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Singleton
public class AuthServiceImpl implements AuthService
{
	Map<String, String> userToPassword = new HashMap<>();
	Map<String, String> tokenToUsername = new HashMap<>();
	
	@Override
	public boolean register(String username, String password) {
		if (userToPassword.containsKey(username)) {
			return false; // already existing user
		}
		userToPassword.put(username, password);
		return true;
	}
	
	@Override
	public String loginAndGetToken(String username, String password) {
		if (userToPassword.containsKey(username) && userToPassword.get(username).equals(password)) {
			String token = generateToken();
			tokenToUsername.put(token, username);
			return token;
		}
		return null;
	}
	
	@Override
	public boolean isTokenValid(String token) {
		return tokenToUsername.containsKey(token);
	}
	
	@Override
	public String generateToken() {
		StringBuilder token = new StringBuilder("T_");
		Random r = new Random();
		
		for (int i = 0; i < 15; i++) {
			String random = "" + (char)(r.nextInt(26) + 'a');
			if(r.nextBoolean()) random = random.toUpperCase();
			token.append(random);
		}
		
		return token.toString();
	}
}
