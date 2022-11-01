package com.github.flightadvisor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.flightadvisor.models.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

}
