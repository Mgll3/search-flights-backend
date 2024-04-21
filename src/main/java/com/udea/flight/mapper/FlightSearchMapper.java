package com.udea.flight.mapper;

import com.udea.flight.domain.dto.FlightSearchDTO;
import com.udea.flight.domain.model.FlightSearch;

public class FlightSearchMapper {

    // Private constructor
    private FlightSearchMapper() {
        // Prevent instantiation
    }

    public static FlightSearchDTO mapToFlightSearchDTO(FlightSearch flightSearch) {
        FlightSearchDTO flightSearchDTO = new FlightSearchDTO();
        flightSearchDTO.setFlightId(flightSearch.getFlightId());
        flightSearchDTO.setDepartureDate(flightSearch.getDepartureDate());
        flightSearchDTO.setArrivalDate(flightSearch.getArrivalDate());
        flightSearchDTO.setOriginCity(flightSearch.getOriginCity());
        flightSearchDTO.setDestinationCity(flightSearch.getDestinationCity());
        flightSearchDTO.setPrice(flightSearch.getPrice());
        flightSearchDTO.setNumberOfPeople(flightSearch.getNumberOfPeople());
        return flightSearchDTO;
    }
}
