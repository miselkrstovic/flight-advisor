package com.github.flightadvisor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.flightadvisor.models.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	City findByName(String name);
	List<City> findAllByName(String keyword);
	
}
