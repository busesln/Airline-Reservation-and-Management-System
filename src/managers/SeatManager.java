package managers;

import models.*;

public class SeatManager {

    // Belirli bir uçuştaki boş koltuk sayısını hesaplama
    public int getEmptySeatsCount(Flight flight) {
        int count = 0;
        Seat[][] seats = flight.getPlane().getSeats();

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] != null && !seats[i][j].isReserved()) {
                    count++;
                }
            }
        }
        return count;
    }

    // Belirli bir koltuğu rezerve etme
    public boolean reserveSeat(Flight flight, int row, int col) throws Exception {
        Seat seat = flight.getPlane().getSeat(row, col);

        if (seat == null) {
            throw new Exception("Seat does not exist at position [" + row + "][" + col + "]");
        }

        if (seat.isReserved()) {
            return false; // Koltuk zaten dolu
        }

        seat.reserve();
        return true;
    }

    // Koltuk rezervasyonunu iptal etme
    public void cancelSeatReservation(Seat seat) {
        seat.cancelReservation();
    }

    // Uçuştaki tüm boş koltukları listeleme
    public void displayAvailableSeats(Flight flight) {
        Seat[][] seats = flight.getPlane().getSeats();
        System.out.println("Available seats for flight " + flight.getFlightNum() + ":");

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] != null && !seats[i][j].isReserved()) {
                    System.out.print(seats[i][j].getSeatNum() + " ");
                }
            }
        }
        System.out.println();
    }

    // Koltuk matrisini görselleştirme (GUI için hazırlık)
    public String[][] getSeatMatrix(Flight flight) {
        Seat[][] seats = flight.getPlane().getSeats();
        String[][] matrix = new String[seats.length][seats[0].length];

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == null) {
                    matrix[i][j] = "NULL";
                } else if (seats[i][j].isReserved()) {
                    matrix[i][j] = "X";
                } else {
                    matrix[i][j] = "O";
                }
            }
        }
        return matrix;
    }
}
