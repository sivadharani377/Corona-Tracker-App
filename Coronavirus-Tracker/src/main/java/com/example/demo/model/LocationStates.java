package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity	
@Table(name = "LocationStates")
public class LocationStates 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int countryid;
	
	private String state;
	private String country;
	private int latestTotalDeaths;
	private int differFromPrevay;
	
	public int getDifferFromPrevay() {
		return differFromPrevay;
	}
	public void setDifferFromPrevay(int differFromPrevay) {
		this.differFromPrevay = differFromPrevay;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestTotalDeaths() {
		return latestTotalDeaths;
	}
	public void setLatestTotalDeaths(int latestTotalDeaths) {
		this.latestTotalDeaths = latestTotalDeaths;
	}
	
	public int getCountryid() {
		return countryid;
	}
	public void setCountryid(int countryid) {
		this.countryid = countryid;
	}
	/*
	 * @Override public String toString() { return "LocationStates [state=" + state
	 * + ", country=" + country + ", latestTotalDeaths=" + latestTotalDeaths + "]";
	 * };
	 */
	@Override
	public String toString() {
		return "LocationStates [countryid=" + countryid + ", state=" + state + ", country=" + country
				+ ", latestTotalDeaths=" + latestTotalDeaths + ", differFromPrevay=" + differFromPrevay + "]";
	}
	
}
