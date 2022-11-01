package com.github.flightadvisor.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.flightadvisor.controllers.requesters.CityRequest;
import com.github.flightadvisor.controllers.requesters.CommentRequest;
import com.github.flightadvisor.models.City;
import com.github.flightadvisor.models.Comment;
import com.github.flightadvisor.services.CityService;

@RestController
public class CityController {

	@Autowired
	CityService cityService;
	
	@GetMapping("/cities")
	public ResponseEntity<List<City>> getCities() {
		return ResponseEntity.ok(cityService.find());
	}

	@PostMapping("/cities")
	public ResponseEntity<String> addCity(@RequestBody CityRequest city) throws Exception {
		if (city != null && !StringUtils.isEmpty(city.name) && !StringUtils.isEmpty(city.country)
				&& !StringUtils.isEmpty(city.description)) {
			try {
				String name = city.name.trim();
				String country = city.country.trim();
				String description = city.description.trim();

				try {
					cityService.add(name, country, description);
					return new ResponseEntity<>("City was successfully added", HttpStatus.CREATED);
				} catch (Exception ex) {
					return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
				}
			} catch (Exception ex) {
				return new ResponseEntity("Illegal object sent. (missing fields)", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity("Illegal object sent.", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/cities/{cityId}/comments")
	public ResponseEntity<String> addComment(@PathVariable Long cityId, @RequestBody CommentRequest comment) throws Exception {
		if (comment != null) {
			try {
				String description = comment.description.trim();

				try {
					cityService.addComment(cityId, description);
					return new ResponseEntity<>("Comment was successfully added", HttpStatus.CREATED);
				} catch (Exception ex) {
					return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
				}
			} catch (Exception ex) {
				return new ResponseEntity("Illegal object sent. (missing fields)", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity("Illegal object sent.", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/cities/{cityId}/comments")
	public ResponseEntity<List<Comment>> getComments(@PathVariable Long cityId) {
		return ResponseEntity.ok(cityService.findComments(cityId));
	}
	
	@PutMapping("/cities/{cityId}/comments/{commentId}")
	public ResponseEntity<String> updateComment(@PathVariable Long cityId, @PathVariable Long commentId,
			@RequestBody CommentRequest comment) throws Exception {
		if (comment != null) {
			try {
				String description = comment.description.trim();

				try {
					cityService.updateComment(cityId, commentId, description);
					return new ResponseEntity<>("Comment was successfully updated", HttpStatus.CREATED);
				} catch (Exception ex) {
					return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
				}
			} catch (Exception ex) {
				return new ResponseEntity("Illegal object sent. (missing fields)", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity("Illegal object sent.", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/cities/{cityId}/comments/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable Long cityId, @PathVariable Long commentId) throws Exception {
		try {
			cityService.deleteComment(cityId, commentId);
			return new ResponseEntity<>("Comment was successfully deleted", HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
		}
	}
}
