/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.udea.flight.domain.dto.FlightSearchDTO
 *  com.udea.flight.domain.model.FlightSearch
 *  com.udea.flight.repository.FlightSearchRepository
 *  com.udea.flight.service.impl.FlightSearchServiceImpl
 *  org.junit.jupiter.api.Assertions
 *  org.junit.jupiter.api.BeforeEach
 *  org.junit.jupiter.api.Test
 *  org.mockito.InjectMocks
 *  org.mockito.Mock
 *  org.mockito.Mockito
 *  org.mockito.MockitoAnnotations
 */
package com.udea.flight.service.impl;

import com.udea.flight.domain.dto.FlightSearchDTO;
import com.udea.flight.domain.model.FlightSearch;
import com.udea.flight.repository.FlightSearchRepository;
import com.udea.flight.service.impl.FlightSearchServiceImpl;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@SpringBootTest
public class FlightSearchServiceImplTest {
    @InjectMocks
    private FlightSearchServiceImpl flightSearchService;
    @Mock
    private FlightSearchRepository flightSearchRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks((Object)this);
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
        Mockito.when((Object)this.flightSearchRepository.findFlightsByDepartureDate(originCity, destinationCity, departureDate)).thenReturn(Arrays.asList(flightSearch));
        List result = this.flightSearchService.searchFlightsByDepartureDate(originCity, destinationCity, departureDate);
        Assertions.assertEquals((int)1, (int)result.size());
        FlightSearchDTO resultFlight = (FlightSearchDTO)result.get(0);
        Assertions.assertEquals((Object)originCity, (Object)resultFlight.getOriginCity());
        Assertions.assertEquals((Object)destinationCity, (Object)resultFlight.getDestinationCity());
        Assertions.assertEquals((Object)departureDate, (Object)resultFlight.getDepartureDate());
    }

    @Test
    public void testSearchFlightsByDepartureAndArrivalDate() {
        String originCity = "OriginCity";
        String destinationCity = "DestinationCity";
        LocalDate departureDate = LocalDate.now();
        LocalDate arrivalDate = LocalDate.now().plusDays(5L);
        FlightSearch flightSearch = new FlightSearch();
        flightSearch.setOriginCity(originCity);
        flightSearch.setDestinationCity(destinationCity);
        flightSearch.setDepartureDate(departureDate);
        flightSearch.setArrivalDate(arrivalDate);
        Mockito.when((Object)this.flightSearchRepository.findFlightsByDepartureAndArrivalDate(originCity, destinationCity, departureDate, arrivalDate)).thenReturn(Arrays.asList(flightSearch));
        List result = this.flightSearchService.searchFlightsByDepartureAndArrivalDate(originCity, destinationCity, departureDate, arrivalDate);
        Assertions.assertEquals((int)1, (int)result.size());
        FlightSearchDTO resultFlight = (FlightSearchDTO)result.get(0);
        Assertions.assertEquals((Object)originCity, (Object)resultFlight.getOriginCity());
        Assertions.assertEquals((Object)destinationCity, (Object)resultFlight.getDestinationCity());
        Assertions.assertEquals((Object)departureDate, (Object)resultFlight.getDepartureDate());
        Assertions.assertEquals((Object)arrivalDate, (Object)resultFlight.getArrivalDate());
    }

    @Test
    public void testSearchFlightsByOriginAndDestination() {
        String originCity = "OriginCity";
        String destinationCity = "DestinationCity";
        FlightSearch flightSearch = new FlightSearch();
        flightSearch.setOriginCity(originCity);
        flightSearch.setDestinationCity(destinationCity);
        Mockito.when((Object)this.flightSearchRepository.findFlightsByOriginAndDestination(originCity, destinationCity)).thenReturn(Arrays.asList(flightSearch));
        List result = this.flightSearchService.searchFlightsByOriginAndDestination(originCity, destinationCity);
        Assertions.assertEquals((int)1, (int)result.size());
        FlightSearchDTO resultFlight = (FlightSearchDTO)result.get(0);
        Assertions.assertEquals((Object)originCity, (Object)resultFlight.getOriginCity());
        Assertions.assertEquals((Object)destinationCity, (Object)resultFlight.getDestinationCity());
    }

    @Test
    public void testSearchFlightsByPrice() {
        Double price = 100.0;
        FlightSearch flightSearch = new FlightSearch();
        flightSearch.setPrice(price);
        Mockito.when((Object)this.flightSearchRepository.findFlightsByPrice(price)).thenReturn(Arrays.asList(flightSearch));
        List result = this.flightSearchService.searchFlightsByPrice(price);
        Assertions.assertEquals((int)1, (int)result.size());
        FlightSearchDTO resultFlight = (FlightSearchDTO)result.get(0);
        Assertions.assertEquals((Double)price, (Double)resultFlight.getPrice());
    }
}
