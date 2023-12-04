package es.ull.flights;

import org.junit.jupiter.api.*;

import es.ull.flights.Flight;
import es.ull.passengers.Passenger;

import static org.junit.jupiter.api.Assertions.*;


public class FlightTest {

	@DisplayName("Tests para los vuelos")
    @Nested
    class SimpleFlightTest {

        private Flight vueloFuerteventura;
        private Flight vueloMalFormado;
        private Flight vueloFuerteventura1Plaza;
        private Passenger laura;
        private Passenger paula;

        @BeforeEach
        void setUp() {
        	vueloFuerteventura = new Flight("FV001", 100);
        	vueloFuerteventura1Plaza = new Flight("FV002", 1);
        	laura = new Passenger("id-FV01-1","Laura", "ES");
        	paula = new Passenger("id-FV01-2","Paula", "ES");
        }

        @Test
        @DisplayName("Generación y getters")
        void testFlightGetters() {
            assertAll("Verifica todas las condiciones para las pruebas de un vuelo normal",
                    () -> assertEquals("FV001", vueloFuerteventura.getFlightNumber()),
                    () -> assertEquals(0, vueloFuerteventura.getNumberOfPassengers())
            );
        }

        @Test
        @DisplayName("Tests Errores de Creación y Máximo Número de Pasajeros")
        void testErrorCreationAndSeatsAvailable() {

        	vueloFuerteventura1Plaza.addPassenger(laura);

        	RuntimeException exceptionFlightBadFormatted = assertThrows(RuntimeException.class,
                    () -> vueloMalFormado = new Flight("FV01", 100));

        	RuntimeException exceptionFlightMaximumPassengers = assertThrows(RuntimeException.class,
                    () -> {
                    	vueloFuerteventura1Plaza.addPassenger(paula);
                    });
        	assertEquals("Invalid flight number", exceptionFlightBadFormatted.getMessage());
        	assertEquals("Not enough seats for flight " + vueloFuerteventura1Plaza.getFlightNumber(), exceptionFlightMaximumPassengers.getMessage());
        }

        @Test
        @DisplayName("Test Añadir y Eliminar Pasajeros")
        void testAddRemovePassengers() {
        	assertAll("Verificar inserción de pasajeros",
        	() -> {
        		vueloFuerteventura.addPassenger(laura);
        		assertEquals(1, vueloFuerteventura.getNumberOfPassengers());
            	vueloFuerteventura.addPassenger(paula);
        		assertEquals(2, vueloFuerteventura.getNumberOfPassengers());
        	});
        	assertAll("Verificar Eliminación de pasajeros",
        	() -> {
        		vueloFuerteventura.removePassenger(paula);
        		assertEquals(1, vueloFuerteventura.getNumberOfPassengers());
            	vueloFuerteventura.removePassenger(laura);
        		assertEquals(0, vueloFuerteventura.getNumberOfPassengers());
        	});
        }
    }
}
