package models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Flight implements Serializable {
	private static final long serialVersionUID = 1L;

	private String flightNum;
	private Route route;
	private Plane plane;
	private LocalDateTime departureTime;
	private int duration;

	public Flight(String flightNum, Route route, Plane plane, LocalDateTime departureTime, int duration) {
		this.flightNum = flightNum;
		this.route = route;
		this.plane = plane;
		this.departureTime = departureTime;
		this.duration = duration;
	}

	public String getFlightNum() {
		return flightNum;
	}

	public Route getRoute() {
		return route;
	}

	public Plane getPlane() {
		return plane;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public int getDuration() {
		return duration;
	}

	public LocalDateTime getArrivalTime() {
		return departureTime.plusMinutes(duration);
	}

	@Override
	public String toString() {
		return "Flight " + flightNum + ": " + route + " @ " + departureTime;
	}
}
