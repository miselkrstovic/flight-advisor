package com.github.flightadvisor.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FA_USERS", schema="FLIGHTADVISOR")
public class User {
	
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
    @Column(name="FIRSTNAME", length=250, nullable=true, unique=false)
	private String firstName;
    
    @Column(name="LASTNAME", length=250, nullable=true, unique=false)
	private String lastName;
	
    @Column(name="USERNAME", length=250, nullable=false, unique=true)
	private String username;
    
    @Column(name="PASSWORD", length=250, nullable=false, unique=false)
	private String password;
	
    @Column(name="SALT", length=250, nullable=false, unique=false)
	private String salt;

	public User() {
		super();
	}

	public User(String firstName, String lastName, String username, String password, String salt) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.salt = salt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

}
