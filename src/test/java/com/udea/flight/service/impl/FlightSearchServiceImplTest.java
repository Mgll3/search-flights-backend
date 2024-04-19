package com.udea.flight.service.impl;

import com.udea.flight.domain.dto.FlightSearchDTO;
import com.udea.flight.domain.model.FlightSearch;
import com.udea.flight.repository.FlightSearchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FlightSearchServiceImplTest {

    @InjectMocks
    private FlightSearchServiceImpl flightSearchService;

    @Mock
    private FlightSearchRepository flightSearchRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchFlightsByDepartureDate() {
        String originCity = "OriginCity";
        String destinationCity = "DestinationCity";
        LocalDate departureDate = LocalDate.now();

        FlightSearch flightSearch = new FlightSearch();
        flightSearch.setOriginCity(originCity);
        flightSearch.setDestinationCity(destinationCity);
        flightSearch.setDepartureDate(departureDate);

        when(flightSearchRepository.findFlightsByDepartureDate(originCity, destinationCity, departureDate))
                .thenReturn(Arrays.asList(flightSearch));

        List<FlightSearchDTO> result = flightSearchService.searchFlightsByDepartureDate(originCity, destinationCity, departureDate);

        assertEquals(1, result.size());
        FlightSearchDTO resultFlight = result.get(0);
        assertEquals(originCity, resultFlight.getOriginCity());
        assertEquals(destinationCity, resultFlight.getDestinationCity());
        assertEquals(departureDate, resultFlight.getDepartureDate());
    }

    @Test
    public void testSearchFlightsByDepartureAndArrivalDate() {
        String originCity = "OriginCity";
        String destinationCity = "DestinationCity";
        LocalDate departureDate = LocalDate.now();
        LocalDate arrivalDate = LocalDate.now().plusDays(5);

        FlightSearch flightSearch = new FlightSearch();
        flightSearch.setOriginCity(originCity);
        flightSearch.setDestinationCity(destinationCity);
        flightSearch.setDepartureDate(departureDate);
        flightSearch.setArrivalDate(arrivalDate);

        when(flightSearchRepository.findFlightsByDepartureAndArrivalDate(originCity, destinationCity, departureDate, arrivalDate))
                .thenReturn(Arrays.asList(flightSearch));

        List<FlightSearchDTO> result = flightSearchService.searchFlightsByDepartureAndArrivalDate(originCity, destinationCity, departureDate, arrivalDate);

        assertEquals(1, result.size());
        FlightSearchDTO resultFlight = result.get(0);
        assertEquals(originCity, resultFlight.getOriginCity());
        assertEquals(destinationCity, resultFlight.getDestinationCity());
        assertEquals(departureDate, resultFlight.getDepartureDate());
        assertEquals(arrivalDate, resultFlight.getArrivalDate());
    }

    @Test
    public void testSearchFlightsByOriginAndDestination() {
        String originCity = "OriginCity";
        String destinationCity = "DestinationCity";

        FlightSearch flightSearch = new FlightSearch();
        flightSearch.setOriginCity(originCity);
        flightSearch.setDestinationCity(destinationCity);

        when(flightSearchRepository.findFlightsByOriginAndDestination(originCity, destinationCity))
                .thenReturn(Arrays.asList(flightSearch));

        List<FlightSearchDTO> result = flightSearchService.searchFlightsByOriginAndDestination(originCity, destinationCity);

        assertEquals(1, result.size());
        FlightSearchDTO resultFlight = result.get(0);
        assertEquals(originCity, resultFlight.getOriginCity());
        assertEquals(destinationCity, resultFlight.getDestinationCity());
    }

    @Test
    public void testSearchFlightsByPrice() {
        Double price = 100.0;

        FlightSearch flightSearch = new FlightSearch();
        flightSearch.setPrice(price);

        when(flightSearchRepository.findFlightsByPrice(price))
                .thenReturn(Arrays.asList(flightSearch));

        List<FlightSearchDTO> result = flightSearchService.searchFlightsByPrice(price);

        assertEquals(1, result.size());
        FlightSearchDTO resultFlight = result.get(0);
        assertEquals(price, resultFlight.getPrice());
    }
}