package es.ull.passengers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import es.ull.flights.Flight;

public class PassengersTest {

	@DisplayName("Tests para pasajeros")
	@Nested
	class PassengersCommonTests {

	    private Flight vueloFuerteventura;
	    private Flight vueloLanzarote;
	    private Passenger laura;
	    private Passenger eduardo;
	    private Passenger paula;

	    @BeforeEach
	    void setUp() {
	    	vueloFuerteventura = new Flight("FV001", 100);
	    	vueloLanzarote = new Flight("LZ001", 82);
	    	laura = new Passenger("id-PS01-1","Laura", "ES");
	    	eduardo = new Passenger("id-PS02-1","Eduardo", "GE");
	    	paula = new Passenger("id-PS02-2","Paula", "US");
	    }

	        @Test
	        @DisplayName("Comprobación de atributos")
	        void testParametersPassenger() {

	        	laura.setFlight(vueloLanzarote);

	            assertAll("Verifica todas las condiciones un intercambio a través de joinFlight",
	                    () -> assertEquals("Miguel", laura.getName()),
	                    () -> assertEquals("GE", eduardo.getCountryCode()),
	                    () -> assertEquals(null, paula.getFlight()),
	                    () -> {
	                    	paula.setFlight(vueloFuerteventura);
	                    	assertEquals("FV001", paula.getFlight().getFlightNumber());
	                    },
	            );
	        }
	}
}
