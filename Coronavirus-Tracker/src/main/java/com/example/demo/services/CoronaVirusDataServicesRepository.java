package com.example.demo.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.LocationStates;

@Repository
public interface CoronaVirusDataServicesRepository extends JpaRepository<LocationStates, Integer>{

	LocationStates findByCountry(String countryName);
	
	@Query(value="SELECT * from Location_States L ORDER BY L.latest_Total_Deaths DESC LIMIT ?1", nativeQuery = true)
	List<LocationStates> findCountryByLatestTotalDeaths(int count);
}
