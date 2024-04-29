package com.udea.flight.domain.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Search_Flights")
public class FlightSearch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_flight")
    private Long flightId;

    @Column(name ="departure_date", nullable = false)
    private String departureDate;

    @Column(name ="arrival_date")
    private String arrivalDate;

    @Column(name = "origin_city", nullable = false)
    private String originCity;

    @Column(name = "destination_city", nullable = false)
    private String destinationCity;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "number_of_people", nullable = false)
    private Integer numberOfPeople;
}
