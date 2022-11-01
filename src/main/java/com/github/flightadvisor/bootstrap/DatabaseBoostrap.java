package com.github.flightadvisor.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.flightadvisor.services.SecurityHelper;
import com.github.flightadvisor.services.UserService;

@Component
public class DatabaseBoostrap implements CommandLineRunner {

	static Logger LOGGER = LoggerFactory.getLogger(DatabaseBoostrap.class);
	
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityHelper securityHelper;
	
	@Override
	public void run(String... args) throws Exception {
		String firstName = "System Administrator";
		String lastName = "";
		String username = "admin";
		String password = "admin123";
		
		userService.add(firstName, lastName, username, password);
		
		LOGGER.info("Added adminstrative account to user table");
	}

}
