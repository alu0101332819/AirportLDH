package es.ull.passengers;

import static org.junit.jupiter.api.Assertions.*;

import es.ull.flights.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Locale;

public class PassengerTest {

    @DisplayName("Passenger Tests")
    @Nested
    class PassengerTests {

        private Passenger passenger;
        private Flight flight;

        @BeforeEach
        void setUp() {
            passenger = new Passenger("id-123", "John Doe", "US");
            flight = new Flight("FL001", 150);
        }

        @Test
        @DisplayName("Check Passenger Information")
        void testGetters() {
            assertEquals("id-123", passenger.getIdentifier());
            assertEquals("John Doe", passenger.getName());
            assertEquals("US", passenger.getCountryCode());
            assertNull(passenger.getFlight());
        }

        @Test
        @DisplayName("Join and Leave Flight")
        void testJoinAndLeaveFlight() {
            assertNull(passenger.getFlight());

            passenger.joinFlight(flight);

            assertNotNull(passenger.getFlight());
            assertEquals("FL001", passenger.getFlight().getFlightNumber());
            assertEquals(1, flight.getNumberOfPassengers());

            passenger.joinFlight(null);

            assertNull(passenger.getFlight());
            assertEquals(0, flight.getNumberOfPassengers());
        }

        @Test
        @DisplayName("Join Another Flight")
        void testJoinAnotherFlight() {
            Flight anotherFlight = new Flight("FL002", 200);

            passenger.joinFlight(flight);
            assertNotNull(passenger.getFlight());
            assertEquals(1, flight.getNumberOfPassengers());

            passenger.joinFlight(anotherFlight);
            assertNotNull(passenger.getFlight());
            assertEquals("FL002", passenger.getFlight().getFlightNumber());
            assertEquals(1, anotherFlight.getNumberOfPassengers());
            assertEquals(0, flight.getNumberOfPassengers());
        }

        @Test
        @DisplayName("Invalid Country Code")
        void testInvalidCountryCode() {
            assertThrows(RuntimeException.class, () -> new Passenger("id-456", "Jane Doe", "XX"));
        }

        @Test
        @DisplayName("Manual Interchange Method")
        void testManualInterchangeMethod() {
            Flight anotherFlight = new Flight("FL003", 120);

            passenger.joinFlight(flight);
            assertNotNull(passenger.getFlight());
            assertEquals(1, flight.getNumberOfPassengers());

            passenger.setFlight(anotherFlight);
            assertNotNull(passenger.getFlight());
            assertEquals("FL003", passenger.getFlight().getFlightNumber());
            assertEquals(1, anotherFlight.getNumberOfPassengers());
            assertEquals(0, flight.getNumberOfPassengers());
        }

        @Test
        @DisplayName("Remove Passenger from Previous Flight")
        void testRemovePassengerFromPreviousFlight() {
            Flight anotherFlight = new Flight("FL004", 100);
            passenger.joinFlight(flight);

            assertNotNull(passenger.getFlight());
            assertEquals(1, flight.getNumberOfPassengers());

            passenger.joinFlight(anotherFlight);
            assertNotNull(passenger.getFlight());
            assertEquals("FL004", passenger.getFlight().getFlightNumber());
            assertEquals(1, anotherFlight.getNumberOfPassengers());
            assertEquals(0, flight.getNumberOfPassengers());
        }

        @Test
        @DisplayName("Attempt to Remove Passenger from Previous Flight Fails")
        void testRemovePassengerFromPreviousFlightFails() {
            Flight anotherFlight = new Flight("FL005", 80);
            passenger.joinFlight(flight);

            assertNotNull(passenger.getFlight());
            assertEquals(1, flight.getNumberOfPassengers());

            // Simulate a failure to remove the passenger from the previous flight
            flight.setCanRemovePassenger(false);

            assertThrows(RuntimeException.class, () -> passenger.joinFlight(anotherFlight));

            // Ensure that the passenger is still in the initial flight
            assertNotNull(passenger.getFlight());
            assertEquals("FL001", passenger.getFlight().getFlightNumber());
            assertEquals(1, flight.getNumberOfPassengers());
        }
    }
}
