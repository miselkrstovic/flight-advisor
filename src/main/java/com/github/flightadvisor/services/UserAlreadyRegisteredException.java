package com.github.flightadvisor.services;

public class UserAlreadyRegisteredException extends Exception {

	public UserAlreadyRegisteredException(String message) {
		super(message);
	}
	
}
