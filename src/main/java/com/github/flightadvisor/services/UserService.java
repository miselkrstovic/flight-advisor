package com.github.flightadvisor.services;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.github.flightadvisor.models.User;
import com.github.flightadvisor.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SecurityHelper securityHelper;
	
	public boolean exists(String username) {
		User user = userRepository.findByUsername(username);
		return user != null;
	}
	
	public boolean authenticate(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		if (username != null) {
			username = username.trim().toLowerCase();
			User user = userRepository.findByUsername(username);
			if (user != null) {
				return securityHelper.check(password, user.getPassword(), user.getSalt());
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public void add(String firstName, String lastName, String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException, PasswordComplexityException, FieldLengthException, UserAlreadyRegisteredException {
		firstName = firstName.trim();
		lastName = lastName.trim();
		username = username.trim().toLowerCase();
		
		int usernameLength = 2;
		if (username.length() >= usernameLength) {
			if (!exists(username)) {
				if (securityHelper.passwordStrength(password) > 1) {
					Pair<String, String> pair = securityHelper.encode(password);
					String passwordHash = pair.getFirst();
					String passwordSalt = pair.getSecond();
					
					User admin = new User(firstName, lastName, username, passwordHash, passwordSalt);
					userRepository.save(admin);
				} else {
					throw new PasswordComplexityException("Password doesn't meet complexity constraints");
				}
			} else {
				throw new UserAlreadyRegisteredException("User is already registered");
			}
		} else {
			throw new FieldLengthException(String.format("Username should be longer than %d characters", usernameLength));
		}
	}
	
}
