package models;

public class Plane {
	private String planeID;
	private String planeModel;
	private int capacity;
	Seat seats[][];
	
	public Plane(String planeID, String planeModel, int rows, int cols){
		this.planeID = planeID;
		this.planeModel = planeModel;
		this.capacity = rows * cols;
		this.seats = new Seat[rows][cols];
	}

}
