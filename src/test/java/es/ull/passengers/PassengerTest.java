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
        @DisplayName("Joining the Same Flight Should Do Nothing")
        void testJoiningSameFlight() {
            passenger.joinFlight(flight);
            Flight previousFlight = passenger.getFlight();

            passenger.joinFlight(flight);

            assertEquals(flight, passenger.getFlight());
            assertEquals(previousFlight, passenger.getFlight());
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

            // Join the initial flight
            passenger.joinFlight(flight);
            assertNotNull(passenger.getFlight());
            assertEquals("FL001", passenger.getFlight().getFlightNumber());

            // Set to another flight
            passenger.joinFlight(anotherFlight);
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
            assertEquals("FL001", passenger.getFlight().getFlightNumber());
            assertEquals(1, flight.getNumberOfPassengers());
        
            // Attempting to remove from the previous flight should return false
            assertFalse(anotherFlight.removePassenger(passenger));
        
            // Ensure that the passenger is still in the initial flight
            assertNotNull(passenger.getFlight());
            assertEquals("FL001", passenger.getFlight().getFlightNumber());
            assertEquals(1, flight.getNumberOfPassengers());
        }

        @Test
        @DisplayName("Add Passenger to Current Flight Fails")
        void testAddPassengerToCurrentFlightFails() {
            passenger.joinFlight(flight);

            assertNotNull(passenger.getFlight());
            assertEquals("FL001", passenger.getFlight().getFlightNumber());
            assertEquals(1, flight.getNumberOfPassengers());

            // Try to add to the current flight again
            assertFalse(flight.addPassenger(passenger));
        
            // Ensure that the passenger count remains the same
            assertEquals(1, flight.getNumberOfPassengers());
        }

        @Test
        @DisplayName("toString() Test")
        void testToString() {
            String expectedString = "Passenger John Doe with identifier: id-123 from US";
            assertEquals(expectedString, passenger.toString());
        }
    }
}
