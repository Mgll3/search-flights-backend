package com.udea.flight.service.impl;

import com.udea.flight.domain.dto.FlightSearchDTO;
import com.udea.flight.domain.model.FlightSearch;
import com.udea.flight.mapper.FlightSearchMapper;
import com.udea.flight.repository.FlightSearchRepository;
import com.udea.flight.service.FlightSearchService;
import com.udea.flight.util.exception.CustomIllegalArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightSearchServiceImpl implements FlightSearchService {
    private FlightSearchRepository flightSearchRepository;

    @Autowired
    public FlightSearchServiceImpl(FlightSearchRepository flightSearchRepository) {
        this.flightSearchRepository = flightSearchRepository;
    }

    @Override
    public List<FlightSearchDTO> searchFlightsByDepartureDate(String originCity, String destinationCity, LocalDate departureDate) {
        List<FlightSearch> flights = flightSearchRepository.findFlightsByDepartureDate(originCity, destinationCity, departureDate);
        return flights.stream()
                .map(FlightSearchMapper::mapToFlightSearchDTO)
                .toList();
    }

    @Override
    public List<FlightSearchDTO> searchFlightsByDepartureAndArrivalDate(String originCity, String destinationCity, LocalDate departureDate, LocalDate arrivalDate) {
        List<FlightSearch> flights = flightSearchRepository.findFlightsByDepartureAndArrivalDate(originCity, destinationCity, departureDate, arrivalDate);
        return flights.stream()
                .map(FlightSearchMapper::mapToFlightSearchDTO)
                .toList();
    }

    @Override
    public List<FlightSearchDTO> searchFlightsByOriginAndDestination(String originCity, String destinationCity) {
        List<FlightSearch> flights = flightSearchRepository.findFlightsByOriginAndDestination(originCity, destinationCity);
        return flights.stream()
                .map(FlightSearchMapper::mapToFlightSearchDTO)
                .toList();
    }

    @Override
    public List<FlightSearchDTO> searchFlightsByPrice(Double price) {
        List<FlightSearch> flights = flightSearchRepository.findFlightsByPrice(price);
        return flights.stream()
                .map(FlightSearchMapper::mapToFlightSearchDTO)
                .toList();
    }

    @Override
    public List<FlightSearchDTO> searchFlightsByNumberOfPeople(Integer numberOfPeople) {
        if (numberOfPeople < 1 || numberOfPeople > 8) {
            throw new CustomIllegalArgumentException("Number of people must be between 1 and 8");
        }

        List<FlightSearch> flights = flightSearchRepository.findFlightsByNumberOfPeople(numberOfPeople);
        return flights.stream()
                .map(FlightSearchMapper::mapToFlightSearchDTO)
                .toList();
    }
}