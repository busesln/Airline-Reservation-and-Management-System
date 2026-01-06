package models;

import java.io.Serializable;

public class Plane implements Serializable {
	private static final long serialVersionUID = 1L;

	private String planeID;
	private String planeModel;
	private int capacity;
	private Seat seats[][];

	public Plane(String planeID, String planeModel, int rows, int cols) {
		this.planeID = planeID;
		this.planeModel = planeModel;
		this.capacity = rows * cols;
		this.seats = new Seat[rows][cols];
	}

	public void createSeats() {
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats[i].length; j++) {
				seats[i][j] = new Seat((i + 1) + " " + (j + 1), 100, SeatType.ECONOMY);
			}
		}
	}

	public String getPlaneID() {
		return planeID;
	}

	public String getPlaneModel() {
		return planeModel;
	}

	public int getCapacity() {
		return capacity;
	}

	public Seat[][] getSeats() {
		return seats;
	}

	public Seat getSeat(int row, int col) {
		if (row >= 0 && row < seats.length && col >= 0 && col < seats[0].length) {
			return seats[row][col];
		}
		return null;
	}

	public void setSeat(int row, int col, Seat seat) {
		if (row >= 0 && row < seats.length && col >= 0 && col < seats[0].length) {
			seats[row][col] = seat;
		}
	}
}
