import java.util.ArrayList;
import java.util.List;

public class Flight {
    private String flightCode;
    private String destination;
    private int economySeats;
    private int businessSeats;
    private List<Passenger> passengers;
    private List<Staff> staff;

    // Constructor
    public Flight(String flightCode, String destination, int economySeats, int businessSeats) {
        this.flightCode = flightCode;
        this.destination = destination;
        this.economySeats = economySeats;
        this.businessSeats = businessSeats;
        this.passengers = new ArrayList<>();
        this.staff = new ArrayList<>();
    }

    // Method to add passengers to the flight (either economy or business class)
    public boolean addPassenger(Passenger passenger, String ticketClass) {
        if (ticketClass.equalsIgnoreCase("economy") && economySeats > passengers.size()) {
            passengers.add(passenger);
            return true;
        } else if (ticketClass.equalsIgnoreCase("business") && businessSeats > passengers.size()) {
            passengers.add(passenger);
            return true;
        }
        return false;
    }

    // Method to assign staff to a flight
    public void assignStaff(Staff staffMember) {
        staff.add(staffMember);
    }

    // Getters
    public String getFlightCode() {
        return flightCode;
    }

    public String getDestination() {
        return destination;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public List<Staff> getStaff() {
        return staff;
    }
}