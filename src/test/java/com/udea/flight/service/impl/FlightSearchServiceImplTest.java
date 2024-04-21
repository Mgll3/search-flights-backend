package com.udea.flight.service.impl;

import com.udea.flight.domain.dto.FlightSearchDTO;
import com.udea.flight.domain.model.FlightSearch;
import com.udea.flight.repository.FlightSearchRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class FlightSearchServiceImplTest {

    @InjectMocks
    private FlightSearchServiceImpl flightSearchService;

    @Mock
    private FlightSearchRepository flightSearchRepository;

    private final LocalDate testDate = LocalDate.of(2024, 1, 1); // Ejemplo de fecha fija

    private FlightSearch createTestFlightSearch(String originCity, String destinationCity, LocalDate departureDate, LocalDate arrivalDate, Double price) {
        FlightSearch flightSearch = new FlightSearch();
        flightSearch.setOriginCity(originCity);
        flightSearch.setDestinationCity(destinationCity);
        flightSearch.setDepartureDate(departureDate);
        flightSearch.setArrivalDate(arrivalDate);
        flightSearch.setPrice(price);
        return flightSearch;
    }

    @Test
    void testSearchFlightsByDepartureDate() {
        String originCity = "OriginCity";
        String destinationCity = "DestinationCity";
        LocalDate departureDate = testDate;

        FlightSearch flightSearch = createTestFlightSearch(originCity, destinationCity, departureDate, null, null);

        when(flightSearchRepository.findFlightsByDepartureDate(originCity, destinationCity, departureDate))
                .thenReturn(Arrays.asList(flightSearch));

        List<FlightSearchDTO> result = flightSearchService.searchFlightsByDepartureDate(originCity, destinationCity, departureDate);

        assertThat(result).hasSize(1)
                .extracting(FlightSearchDTO::getOriginCity, FlightSearchDTO::getDestinationCity, FlightSearchDTO::getDepartureDate)
                .containsExactly(tuple(originCity, destinationCity, departureDate));
    }

    @Test
    void testSearchFlightsByDepartureAndArrivalDate() {
        String originCity = "OriginCity";
        String destinationCity = "DestinationCity";
        LocalDate departureDate = testDate;
        LocalDate arrivalDate = testDate.plusDays(5);

        FlightSearch flightSearch = createTestFlightSearch(originCity, destinationCity, departureDate, arrivalDate, null);

        when(flightSearchRepository.findFlightsByDepartureAndArrivalDate(originCity, destinationCity, departureDate, arrivalDate))
                .thenReturn(Arrays.asList(flightSearch));

        List<FlightSearchDTO> result = flightSearchService.searchFlightsByDepartureAndArrivalDate(originCity, destinationCity, departureDate, arrivalDate);

        assertThat(result).hasSize(1)
                .extracting(FlightSearchDTO::getOriginCity, FlightSearchDTO::getDestinationCity, FlightSearchDTO::getDepartureDate, FlightSearchDTO::getArrivalDate)
                .containsExactly(tuple(originCity, destinationCity, departureDate, arrivalDate));
    }
    // Similar pattern can be applied to other methods
}
