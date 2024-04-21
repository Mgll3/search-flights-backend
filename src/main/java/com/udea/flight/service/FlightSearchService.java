package com.udea.flight.service;

import com.udea.flight.domain.dto.FlightSearchDTO;

import java.time.LocalDate;
import java.util.List;

public interface FlightSearchService {

    List<FlightSearchDTO> searchFlightsByDepartureDate(String originCity, String destinationCity, LocalDate departureDate);

    List<FlightSearchDTO> searchFlightsByDepartureAndArrivalDate(String originCity, String destinationCity, LocalDate departureDate, LocalDate arrivalDate);

    List<FlightSearchDTO> searchFlightsByOriginAndDestination(String originCity, String destinationCity);

    List<FlightSearchDTO> searchFlightsByPrice(Double price);

    List<FlightSearchDTO> searchFlightsByNumberOfPeople(Integer numberOfPeople);
}
