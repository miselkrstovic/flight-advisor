package com.github.flightadvisor.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FA_ROUTES", schema="FLIGHTADVISOR")
public class Route {
	
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
    
//	  airline
//	  airline_id
//	  source_airport
//	  source_airport_id
//	  destination_airport
//	  destination_airport_id
//	  codeshare
//	  stops
//	  equipment
//	  price
	  
}
