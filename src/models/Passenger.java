package models;

import java.io.Serializable;

public class Passenger implements Serializable {
	private static final long serialVersionUID = 1L;

	private String passengerID;
	private String name;
	private String surname;
	private String contactInfo;

	public Passenger(String passengerID, String name, String surname, String contactInfo) {
		this.passengerID = passengerID;
		this.name = name;
		this.surname = surname;
		this.contactInfo = contactInfo;
	}

	public String getPassengerID() {
		return passengerID;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public String getFullName() {
		return name + " " + surname;
	}

	@Override
	public String toString() {
		return "Passenger: " + getFullName() + " (ID: " + passengerID + ")";
	}
}
