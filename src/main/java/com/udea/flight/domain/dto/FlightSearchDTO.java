package com.udea.flight.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlightSearchDTO {

    private Long flightId;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private String originCity;
    private String destinationCity;
    private Double price;
    private Integer numberOfPeople;
}
