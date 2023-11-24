package es.ull.flightspassengers;

import es.ull.flights.Flight;
import es.ull.passengers.Passenger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlightPassengerTest {

    @Test
    void testFlightNumberValidation() {
        assertThrows(RuntimeException.class, () -> new Flight("123", 100));
        assertDoesNotThrow(() -> new Flight("AA1234", 100));
    }

    @Test
    void testAddPassenger() {
        Flight flight = new Flight("AA1234", 1);
        Passenger passenger1 = new Passenger("ID1", "John Doe", "US");
        Passenger passenger2 = new Passenger("ID2", "Jane Doe", "US");

        assertTrue(flight.addPassenger(passenger1));
        assertThrows(RuntimeException.class, () -> flight.addPassenger(passenger2));
    }
}
