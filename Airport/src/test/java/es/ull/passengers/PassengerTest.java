package es.ull.passengers;

import es.ull.flights.Flight;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {

    @Test
    void testCountryCodeValidation() {
        assertThrows(RuntimeException.class, () -> new Passenger("ID1", "John Doe", "XX"));
        assertDoesNotThrow(() -> new Passenger("ID1", "John Doe", "US"));
    }

    @Test
    void testJoinFlight() {
        Flight flight = new Flight("AA1234", 1);
        Passenger passenger1 = new Passenger("ID1", "John Doe", "US");
        Passenger passenger2 = new Passenger("ID2", "Jane Doe", "US");

        assertDoesNotThrow(() -> passenger1.joinFlight(flight));
        assertThrows(RuntimeException.class, () -> passenger2.joinFlight(flight));
    }
}
