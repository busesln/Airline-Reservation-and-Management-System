package models;

import java.io.Serializable;

public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	private String ticketID;
	private Reservation reservation;
	private double price;
	private Baggage baggage;

	public Ticket(String ticketID, Reservation reservation, double price, Baggage baggage) {
		this.ticketID = ticketID;
		this.reservation = reservation;
		this.price = price;
		this.baggage = baggage;
	}

	public String getTicketID() {
		return ticketID;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public double getPrice() {
		return price;
	}

	public Baggage getBaggage() {
		return baggage;
	}

	@Override
	public String toString() {
		return "Ticket " + ticketID + ": " + reservation.getReservationCode() +
				" - Price: " + price + " TL, " + baggage;
	}
}
