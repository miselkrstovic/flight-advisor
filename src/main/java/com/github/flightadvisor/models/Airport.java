package com.github.flightadvisor.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FA_AIRPORTS", schema="FLIGHTADVISOR")
public class Airport {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
    
//	  airport_id,
//	  name,
//	  city,
//	  country,
//	  iata,
//	  icao,
//	  latitude,
//	  longitude
//	  altitude,
//	  timezone,
//	  dst,
//	  tz database time zone,
//	  type,
//	  source
	  
}
