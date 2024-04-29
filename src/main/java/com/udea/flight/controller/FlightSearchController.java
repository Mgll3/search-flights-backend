package com.udea.flight.controller;

import com.udea.flight.domain.dto.FlightSearchDTO;
import com.udea.flight.service.FlightSearchService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/flights/v1")
@CrossOrigin(origins = "*")
public class FlightSearchController {

    private final FlightSearchService flightSearchService;

    public FlightSearchController(FlightSearchService flightSearchService) {
        this.flightSearchService = flightSearchService;
    }

    @GetMapping("/searchByDepartureDate")
    public List<FlightSearchDTO> searchFlightsByDepartureDate(@RequestParam String originCity, @RequestParam String destinationCity, @RequestParam String departureDate) {
        List<FlightSearchDTO> flights = flightSearchService.searchFlightsByDepartureDate(originCity, destinationCity, departureDate);
        
        return flights;
        
    }

    @GetMapping("/searchByDepartureAndArrivalDate")
    public List<FlightSearchDTO> searchFlightsByDepartureAndArrivalDate(@RequestParam String originCity, @RequestParam String destinationCity, @RequestParam String departureDate, @RequestParam String arrivalDate) {
        return flightSearchService.searchFlightsByDepartureAndArrivalDate(originCity, destinationCity, departureDate, arrivalDate);
    }

    @GetMapping("/searchByOriginAndDestination")
    public List<FlightSearchDTO> searchFlightsByOriginAndDestination(@RequestParam String originCity, @RequestParam String destinationCity) {
        return flightSearchService.searchFlightsByOriginAndDestination(originCity, destinationCity);
    }

    @GetMapping("/searchByPrice")
    public List<FlightSearchDTO> searchFlightsByPrice(@RequestParam Double price) {
        return flightSearchService.searchFlightsByPrice(price);
    }

    @GetMapping("/searchByNumberOfPeople")
    public List<FlightSearchDTO> searchFlightsByNumberOfPeople(@RequestParam Integer numberOfPeople) {
        return flightSearchService.searchFlightsByNumberOfPeople(numberOfPeople);
    }
}