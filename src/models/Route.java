package models;

import java.io.Serializable;

public class Route implements Serializable {
	private static final long serialVersionUID = 1L;

	private String departurePlace;
	private String arrivalPlace;
	private double basePrice;

	public Route(String departurePlace, String arrivalPlace, double basePrice) {
		this.departurePlace = departurePlace;
		this.arrivalPlace = arrivalPlace;
		this.basePrice = basePrice;
	}

	public String getDeparturePlace() {
		return departurePlace;
	}

	public String getArrivalPlace() {
		return arrivalPlace;
	}

	public double getBasePrice() {
		return basePrice;
	}

	@Override
	public String toString() {
		return departurePlace + " -> " + arrivalPlace;
	}

}
