package edu.ada.micronaut.controller;

public interface AuthController
{
	Object register(String username, String password);
	Object login(String username, String password);
}
