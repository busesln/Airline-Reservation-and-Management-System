package threading;

import javax.swing.SwingWorker;
import managers.FlightManager;
import models.Flight;
import models.Seat;
import java.util.List;

public class ReportGenerator extends SwingWorker<String, String> {
    private final FlightManager flightManager;

    public ReportGenerator(FlightManager flightManager) {
        this.flightManager = flightManager;
    }

    @Override
    protected String doInBackground() throws Exception {
        publish("Preparing report...");

        Thread.sleep(2000);

        List<Flight> flights = flightManager.getAllFlights();
        int totalFlights = flights.size();

        publish("Calculating occupancy rates...");
        Thread.sleep(1500);

        StringBuilder report = new StringBuilder();
        report.append("=== FLIGHT OCCUPANCY REPORT ===\n\n");
        report.append("Total Flights: ").append(totalFlights).append("\n\n");

        for (Flight flight : flights) {
            int totalSeats = flight.getPlane().getCapacity();
            int occupiedSeats = countOccupiedSeats(flight);
            double occupancyRate = (occupiedSeats * 100.0) / totalSeats;

            report.append("Flight ").append(flight.getFlightNum()).append(":\n");
            report.append("  Route: ").append(flight.getRoute()).append("\n");
            report.append("  Occupancy: ").append(occupiedSeats).append("/").append(totalSeats);
            report.append(" (").append(String.format("%.2f", occupancyRate)).append("%)\n\n");

            Thread.sleep(500);
        }

        publish("Report generation complete!");
        return report.toString();
    }

    @Override
    protected void process(List<String> chunks) {
        for (String message : chunks) {
            System.out.println(message);
        }
    }

    private int countOccupiedSeats(Flight flight) {
        int count = 0;
        Seat[][] seats = flight.getPlane().getSeats();
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] != null && seats[i][j].isReserved()) {
                    count++;
                }
            }
        }
        return count;
    }
}
