package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.LocationStates;
import com.example.demo.services.CoronaVirusDataServices;
import com.example.demo.services.CoronaVirusDataServicesRepository;

@Controller
public class HomeController 
{
	@Autowired
	CoronaVirusDataServicesRepository repository;
	
	CoronaVirusDataServices crnService;
	
	@Autowired
	public void setCrnService(CoronaVirusDataServices crnService) {
		this.crnService = crnService;
	}
	
	@GetMapping("/")
	public String home(Model model)
	{
		List<LocationStates> allstates=crnService.getAllstates();
		int totalDeathsReported=allstates.stream().mapToInt(stat->stat.getLatestTotalDeaths()).sum();
		model.addAttribute("LocationStates",allstates);
		model.addAttribute("totalDeathsReported",totalDeathsReported);
		repository.saveAll(allstates);
		return "home";
	}
	
	@RequestMapping(path = "/collectChartData", produces = {"application/json"})
	@ResponseBody 
	public List<LocationStates> collectCharData(Model m)
	{
		System.out.println("Here View Chart Data");
		List<LocationStates> allstates=crnService.getAllstates();
		int totalDeathsReported=allstates.stream().mapToInt(stat->stat.getLatestTotalDeaths()).sum();
		m.addAttribute("LocationStates", allstates);
		m.addAttribute("totalDeathsReported",totalDeathsReported);
		return allstates;
	}
	
	@RequestMapping(path = "/collectChartData/{id}", produces = {"application/json"})
	@ResponseBody 
	public Optional <LocationStates> collectChartDataByCountryID(@PathVariable("id") int countryid, Model m)
	{
	    System.out.println("Here View Chart Data by Country ID...");
	    Optional<LocationStates> locationStates = repository.findById(countryid);
	    return locationStates; 
	}
	
	@RequestMapping(path = "/collectChartData/country={name}", produces = {"application/json"})
	@ResponseBody 
	public LocationStates collectChartDataByCountrName(@PathVariable("name") String countryName, Model m)
	{
	    System.out.println("Here View Chart Data by Country Name...");
	    LocationStates locationStates = repository.findByCountry(countryName);
	    return locationStates; 
	}
	
	@RequestMapping(path = "/collectChartData/top={count}", produces = {"application/json"})
	@ResponseBody 
	public List<LocationStates> collectChartDataByCountryTop(@PathVariable("count") int count, Model m)
	{
		System.out.println("Here View Chart Data by Country Name...");
	    List<LocationStates> locationStates = repository.findCountryByLatestTotalDeaths(count);
	    return locationStates;
	}
	
	@RequestMapping(value = "/viewChart", method = RequestMethod.GET)
	public ModelAndView viewChart() {
		return new ModelAndView("/viewChart").addObject("myURL", new String("http://localhost:8082/collectChartData"));
	}

}
