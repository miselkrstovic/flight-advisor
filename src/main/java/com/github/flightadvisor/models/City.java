package com.github.flightadvisor.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="FA_CITIES", schema="FLIGHTADVISOR")
public class City {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
    
    @Column(name="NAME", length=250, nullable=false, unique=true)
    private String name;
    
    @Column(name="COUNTRY", length=250, nullable=false, unique=false)
    private String country;
    
    @Column(name="DESCRIPTION", length=250, nullable=false, unique=false)
    private String description;

    @OneToMany
    @JoinColumn(name = "city_id")
    private Set<Comment> comments = new HashSet<>();
    
	public City() {
		super();
	}

	public City(String name, String country, String description) {
		super();
		this.name = name;
		this.country = country;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

}
