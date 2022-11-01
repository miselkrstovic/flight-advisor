package com.github.flightadvisor.services;

public class CityAlreadyExistsException extends Exception {
	
	public CityAlreadyExistsException(String message) {
		super(message);
	}

}
