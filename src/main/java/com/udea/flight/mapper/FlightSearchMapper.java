package com.udea.flight.mapper;

import com.udea.flight.domain.dto.FlightSearchDTO;
import com.udea.flight.domain.model.FlightSearch;

public class FlightSearchMapper {
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

    public static FlightSearch mapToFlightSearch(FlightSearchDTO flightSearchDto) {
        FlightSearch flightSearch = new FlightSearch();
        flightSearch.setFlightId(flightSearchDto.getFlightId());
        flightSearch.setDepartureDate(flightSearchDto.getDepartureDate());
        flightSearch.setArrivalDate(flightSearchDto.getArrivalDate());
        flightSearch.setOriginCity(flightSearchDto.getOriginCity());
        flightSearch.setDestinationCity(flightSearchDto.getDestinationCity());
        flightSearch.setPrice(flightSearchDto.getPrice());
        flightSearch.setNumberOfPeople(flightSearchDto.getNumberOfPeople());
        return flightSearch;
    }
}
