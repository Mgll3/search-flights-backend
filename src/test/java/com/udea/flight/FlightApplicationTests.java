package com.udea.flight;

import com.udea.flight.service.FlightSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class FlightApplicationTests {

	@Autowired
	private FlightSearchService flightSearchService;

	@Test
	void contextLoads() {
		assertNotNull(flightSearchService);
	}

}