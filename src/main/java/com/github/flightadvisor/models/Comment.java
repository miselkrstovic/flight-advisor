package com.github.flightadvisor.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FA_COMMENTS", schema = "FLIGHTADVISOR")
public class Comment {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="DESCRIPTION", length=250, nullable=false, unique=false)
	private String description;
	
	@Column(name="CREATED", length=250, nullable=false, unique=false)
	private LocalDateTime created;
	
	@Column(name="MODIFIED", length=250, nullable=false, unique=false)
	private LocalDateTime modified;
	
	@ManyToOne
	private City city;
	
	public Comment() {
		super();
	}

	public Comment(String description, LocalDateTime created, LocalDateTime modified) {
		super();
		this.description = description;
		this.created = created;
		this.modified = modified;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getModified() {
		return modified;
	}

	public void setModified(LocalDateTime modifies) {
		this.modified = modifies;
	}

}
