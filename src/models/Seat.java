package models;

import java.io.Serializable;

public class Seat implements Serializable {
private static final long serialVersionUID = 1L;
	
	private String seatNum;
	private double price;
	private boolean isReserved;
	private SeatType type;

	public Seat(String seatNum, double price, SeatType  type) {
		this.seatNum = seatNum;
		this.price = price;
		this.type = type;
		this.isReserved = false;
	}

	public String getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public SeatType getType() {
		return type;
	}

	public void setType(SeatType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
	    return "Koltuk: " + seatNum + " (" + type + ") - " + (isReserved ? "Dolu" : "Boş");
	}

}
