package com.github.flightadvisor.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.flightadvisor.controllers.requesters.UserRequest;
import com.github.flightadvisor.services.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/users/login")
	public ResponseEntity<String> login(@RequestBody UserRequest user) throws NoSuchAlgorithmException, InvalidKeySpecException {
		if (user != null && !StringUtils.isEmpty(user.username) 
				&& !StringUtils.isEmpty(user.password)) {
			
			if (userService.authenticate(user.username, user.password)) { 
				return ResponseEntity.ok("User authenticated.");
			} else {
				return new ResponseEntity("Failed to authenticate user.", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity("Illegal object sent.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/users")
	public ResponseEntity<String> register(@RequestBody UserRequest user) throws Exception {
		if (user != null && !StringUtils.isEmpty(user.username) 
				&& !StringUtils.isEmpty(user.password)) {
			try {
				String firstName = user.firstName.trim();
				String lastName = user.lastName.trim();
				String username = user.username.trim().toLowerCase();
				String password = user.password;
				
				try {
					userService.add(firstName, lastName, username, password);
					return new ResponseEntity<>("User was successfully added", HttpStatus.CREATED);
				} catch(Exception ex) {
					return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
				}
			} catch(Exception ex) {
				return new ResponseEntity("Illegal object sent. (missing fields)", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity("Illegal object sent.", HttpStatus.BAD_REQUEST);
		}
	}

}
