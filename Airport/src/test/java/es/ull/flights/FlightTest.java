package es.ull.flights;

import es.ull.passengers.Passenger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlightTest {
    private Flight flight;
    private Passenger passenger;

    @BeforeEach
    void setUp() {
        passenger = new Passenger();
        flight = new Flight("AA1234", 2);
    }

    @Test
    void testConstructor() {
        assertThrows(RuntimeException.class, () -> new Flight("1234", 2));
        assertThrows(RuntimeException.class, () -> new Flight("AA12", 2));
        assertDoesNotThrow(() -> new Flight("AA1234", 2));
    }

    @Test
    void testGetFlightNumber() {
        assertEquals("AA1234", flight.getFlightNumber());
    }

    @Test
    void testGetNumberOfPassengers() {
        assertEquals(0, flight.getNumberOfPassengers());
        flight.addPassenger(passenger);
        assertEquals(1, flight.getNumberOfPassengers());
    }

    @Test
    void testAddPassenger() {
        assertTrue(flight.addPassenger(passenger));
        assertEquals(1, flight.getNumberOfPassengers());
        assertThrows(RuntimeException.class, () -> {
            flight.addPassenger(new Passenger());
            flight.addPassenger(new Passenger());
        });
    }

    @Test
    void testRemovePassenger() {
        assertFalse(flight.removePassenger(passenger));
        flight.addPassenger(passenger);
        assertTrue(flight.removePassenger(passenger));
    }
}
