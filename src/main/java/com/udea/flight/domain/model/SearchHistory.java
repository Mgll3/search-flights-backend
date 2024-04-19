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
@Table(name = "Search_History")
public class SearchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_search_history")
    private Long searchHistoryId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "flight_id", nullable = false)
    private Long flightId;

    @Column(name = "search_date", nullable = false)
    private LocalDate searchDate;

    // Assuming User and FlightSearch classes exist and are properly annotated
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_flight", insertable = false, updatable = false)
    private FlightSearch flightSearch;
}