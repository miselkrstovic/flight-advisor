package com.github.flightadvisor.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.flightadvisor.models.City;
import com.github.flightadvisor.services.CityService;

@RestController
public class SearchController {

	@Autowired
	CityService cityService;
	
	@GetMapping("/search")
	public ResponseEntity<List<City>> getCities(@RequestParam String keyword, @RequestParam Long limit) {
		return ResponseEntity.ok(cityService.find(keyword, limit));
	}

}
