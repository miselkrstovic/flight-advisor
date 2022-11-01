package com.github.flightadvisor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.flightadvisor.models.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

}
