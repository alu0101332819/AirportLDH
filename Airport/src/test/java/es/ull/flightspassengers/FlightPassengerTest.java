package es.ull.flightspassengers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import es.ull.flights.Flight;
import es.ull.passengers.Passenger;

public class FlightPassengersTest {

	@DisplayName("Tests intercambio correcto pasajeros")
	@Nested
	class InterchangeFlightsPassengers {

	    private Flight vueloFuerteventura;
	    private Flight vueloLanzarote;
	    private Passenger jaime;

	    @BeforeEach
	    void setUp() {
	    	vueloFuerteventura = new Flight("FV001", 100);
	    	vueloLanzarote = new Flight("LZ001", 82);
	    	jaime = new Passenger("id-PS01-2","Jaime", "ES");
	    }

	        @Test
	        @DisplayName("Intercambio correcto entre vuelos a través de joinFlight")
	        void testMethodInterchangePassenger() {

	        	jaime.joinFlight(vueloLanzarote);
	        	jaime.joinFlight(vueloFuerteventura);

	            assertAll("Verifica todas las condiciones un intercambio a través de joinFlight",
	                    () -> assertEquals("FV001", jaime.getFlight().getFlightNumber()),
	                    () -> assertEquals(1, vueloFuerteventura.getNumberOfPassengers()),
	                    () -> assertEquals(0, vueloLanzarote.getNumberOfPassengers())
	            );
	        }

	        @Test
	        @DisplayName("Intercambio manual entre vuelos a través de setFlight and removePassenger")
	        void testManualInterchangeMethod() {

	        	jaime.joinFlight(vueloFuerteventura);
	        	jaime.setFlight(vueloLanzarote);

	        	assertAll("Intercambio a través de removePassenger y setFlight",
	        	() -> assertEquals("LZ001", jaime.getFlight().getFlightNumber()),
	        	() -> assertEquals(1, vueloFuerteventura.getNumberOfPassengers()),
	        	() -> assertEquals(0, vueloLanzarote.getNumberOfPassengers()),
	        	() -> {
	        		vueloLanzarote.addPassenger(jaime);
	        		assertEquals(1, vueloLanzarote.getNumberOfPassengers());
	        	},
	        	() -> assertEquals(1, vueloFuerteventura.getNumberOfPassengers()),
	        	() -> {
	        		vueloFuerteventura.removePassenger(jaime);
	        		assertEquals(0, vueloFuerteventura.getNumberOfPassengers());
	        	});
	        }
	    }
}
