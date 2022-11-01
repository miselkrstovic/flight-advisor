package com.github.flightadvisor.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.flightadvisor.models.City;
import com.github.flightadvisor.models.Comment;
import com.github.flightadvisor.repositories.CityRepository;
import com.github.flightadvisor.repositories.CommentRepository;

@Service
public class CityService {

	final private Long WITHOUT_LIMIT = 0L;
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	public boolean exists(String name) {
		City city = cityRepository.findByName(name);
		return city != null;
	}
	
	public List<City> find() {
		return find(null, WITHOUT_LIMIT);
	}
	
	public List<City> find(String keyword, Long limit) {
		if (keyword == null) {
			return cityRepository.findAll();
		} else {
			return cityRepository.findAllByName(keyword);
		}
	}
		
	public void add(String name, String country, String description) throws CityAlreadyExistsException {
		name = name.trim();
		country = country.trim();
		description = description.trim();
		
		if (!exists(name)) {		
			City city = new City(name, country, description);
			cityRepository.save(city);
		} else {
			throw new CityAlreadyExistsException("City is already added");
		}
	}
	
	@Transactional
	public void addComment(Long cityId, String description) {
		if (description != null && description.trim().length() > 0) { 
			Optional<City> cityOpt = cityRepository.findById(cityId);
			if (cityOpt != null && cityOpt.get() != null) {
				City city = cityOpt.get();
				
				LocalDateTime date = LocalDateTime.now();
				Comment comment = new Comment(description, date, date);
				city.getComments().add(comment);
				
				commentRepository.save(comment);
				cityRepository.save(city);
			}
		}
	}

	public void updateComment(Long cityId, Long commentId, String description) throws NoSuchRecordException {
		if (description != null && description.trim().length() > 0) { 
			Optional<City> cityOpt = cityRepository.findById(cityId);
			if (cityOpt != null && cityOpt.get() != null) {
				City city = cityOpt.get();
				
				LocalDateTime modifiedDate = LocalDateTime.now();
				Optional<Comment> commentOpt = commentRepository.findById(commentId);
				if (commentOpt != null && commentOpt.get() != null) {
					Comment comment = commentOpt.get();
					comment.setDescription(description);
					comment.setModified(modifiedDate);
					city.getComments().add(comment);
				
					commentRepository.save(comment);
					cityRepository.save(city);
				} else {
					throw new NoSuchRecordException("No matching record found");
				}
			}
		}
	}

	public void deleteComment(Long cityId, Long commentId)  throws NoSuchRecordException {
		Optional<City> cityOpt = cityRepository.findById(cityId);
		if (cityOpt != null && cityOpt.get() != null) {
			City city = cityOpt.get();
			
			Optional<Comment> commentOpt = commentRepository.findById(commentId);
			if (commentOpt != null && commentOpt.get() != null) {
				Comment comment = commentOpt.get();
				if (city.getComments().contains(comment)) {
					commentRepository.deleteById(commentId);
				} else { 
					throw new NoSuchRecordException("No matching record found");
				}
			} else {
				throw new NoSuchRecordException("No matching record found");
			}
		}		
	}

	public List<Comment> findComments(Long cityId) {
		return commentRepository.findAllByCityId(cityId);
	}

}
