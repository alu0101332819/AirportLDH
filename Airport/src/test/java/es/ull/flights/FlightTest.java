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
        private Passenger miguel;
        private Passenger jaime;

        @BeforeEach
        void setUp() {
        	vueloFuerteventura = new Flight("FV001", 100);
        	vueloFuerteventura1Plaza = new Flight("FV002", 1);
        	miguel = new Passenger("id-FV01-1","Miguel", "ES");
        	jaime = new Passenger("id-FV01-2","Jaime", "ES");
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
        @DisplayName("Tests Errores de Creación y Maximo numero de Pasajeros")
        void testErrorCreationAndSeatsAvailable() {

        	vueloFuerteventura1Plaza.addPassenger(miguel);

        	RuntimeException exceptionFlightBadFormatted = assertThrows(RuntimeException.class,
                    () -> vueloMalFormado = new Flight("FV01", 100));

        	RuntimeException exceptionFlightMaximumPassengers = assertThrows(RuntimeException.class,
                    () -> {
                    	vueloFuerteventura1Plaza.addPassenger(jaime);
                    });
        	assertEquals("Invalid flight number", exceptionFlightBadFormatted.getMessage());
        	assertEquals("Not enough seats for flight " + vueloFuerteventura1Plaza.getFlightNumber(), exceptionFlightMaximumPassengers.getMessage());
        }

        @Test
        @DisplayName("Test Añadir y Eliminar Pasajeros")
        void testAddRemovePassengers() {
        	assertAll("Verificar inserción de pasajeros",
        	() -> {
        		vueloFuerteventura.addPassenger(miguel);
        		assertEquals(1, vueloFuerteventura.getNumberOfPassengers());
            	vueloFuerteventura.addPassenger(jaime);
        		assertEquals(2, vueloFuerteventura.getNumberOfPassengers());
        	});
        	assertAll("Verificar Eliminación de pasajeros",
        	() -> {
        		vueloFuerteventura.removePassenger(jaime);
        		assertEquals(1, vueloFuerteventura.getNumberOfPassengers());
            	vueloFuerteventura.removePassenger(miguel);
        		assertEquals(0, vueloFuerteventura.getNumberOfPassengers());
        	});
        }
    }
}
