package interfaces;

import models.Flight;
import java.util.List;

public interface IFlightManager {

    void addFlight(Flight flight) throws Exception;

    boolean removeFlight(String flightNum) throws Exception;

    List<Flight> getAllFlights();

    Flight getFlightByNumber(String flightNum);

    List<Flight> searchByDeparture(String departurePlace);

    List<Flight> searchByArrival(String arrivalPlace);

    List<Flight> searchByRoute(String departurePlace, String arrivalPlace);

    int getFlightCount();
}
