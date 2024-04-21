package com.udea.flight.repository;

import com.udea.flight.domain.model.FlightSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightSearchRepository extends JpaRepository<FlightSearch, Long>{

    @Query(value = "SELECT * FROM Search_Flights WHERE origin_city = :originCity AND destination_city = :destinationCity AND departure_date = :departureDate", nativeQuery = true)
    List<FlightSearch> findFlightsByDepartureDate(@Param("originCity") String originCity, @Param("destinationCity") String destinationCity, @Param("departureDate") LocalDate departureDate);

    @Query(value = "SELECT * FROM Search_Flights WHERE origin_city = :originCity AND destination_city = :destinationCity AND departure_date = :departureDate AND arrival_date = :arrivalDate", nativeQuery = true)
    List<FlightSearch> findFlightsByDepartureAndArrivalDate(@Param("originCity") String originCity, @Param("destinationCity") String destinationCity, @Param("departureDate") LocalDate departureDate, @Param("arrivalDate") LocalDate arrivalDate);

    @Query(value = "SELECT * FROM Search_Flights WHERE origin_city = :originCity AND destination_city = :destinationCity", nativeQuery = true)
    List<FlightSearch> findFlightsByOriginAndDestination(@Param("originCity") String originCity, @Param("destinationCity") String destinationCity);

    @Query(value = "SELECT * FROM Search_Flights WHERE price <= :price", nativeQuery = true)
    List<FlightSearch> findFlightsByPrice(@Param("price") Double price);

    @Query(value = "SELECT * FROM Search_Flights WHERE number_of_people = :numberOfPeople", nativeQuery = true)
    List<FlightSearch> findFlightsByNumberOfPeople(@Param("numberOfPeople") Integer numberOfPeople);
}